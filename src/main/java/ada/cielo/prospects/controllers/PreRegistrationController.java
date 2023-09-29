package ada.cielo.prospects.controllers;

import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import ada.cielo.prospects.model.schemas.ResponseSchema;
import org.springframework.data.domain.Sort;
import ada.cielo.prospects.services.MCCodeService;
import ada.cielo.prospects.services.PreRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/v1/pre-registrations")
@Tag(name = "Pre Registrations", description = "Endpoints for managing pre-registrations")
public class PreRegistrationController {

    private final PreRegistrationService preRegistrationService;
    private final MCCodeService mcCodeService;
    public PreRegistrationController(PreRegistrationService preRegistrationService, MCCodeService mcCodeService) {
        this.preRegistrationService = preRegistrationService;
        this.mcCodeService = mcCodeService;
    }

    @GetMapping("")
    @Operation(summary = "List Pre Registrations")
    public ResponseEntity<ResponseSchema> index(@RequestParam(required = false) String search_term, @RequestParam(required = false) String order_by) {
        try {
            List<PreRegistrationSchema> preRegistrationList = this.preRegistrationService.findByTerm(search_term, order_by);
            return ResponseEntity.ok(new ResponseSchema("Success", "Listing Data", preRegistrationList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new Pre Registration")
    public ResponseEntity<ResponseSchema> add(@Valid @RequestBody PreRegistrationSchema preRegistrationSchema) {
        try {
            preRegistrationSchema.setMcCode(mcCodeService.findById(preRegistrationSchema.getMcCodeId()));
            preRegistrationSchema.setOp("Created");
            preRegistrationSchema.setAt(LocalDateTime.now());

            preRegistrationSchema.validate();
            preRegistrationSchema = this.preRegistrationService.save(preRegistrationSchema);
            return ResponseEntity.ok(new ResponseSchema("Success", "New Pre Registration created successfully", preRegistrationSchema));
        } catch (Exception e) {
            String eDetail = e.getMessage();
            Pattern pattern = Pattern.compile("Detail:.*?\\]");
            Matcher matcher = pattern.matcher(eDetail);
            if (matcher.find()) {
                eDetail = matcher.group();
            }
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", eDetail, preRegistrationSchema));
        }
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Store the changes of a Pre Registration")
    public ResponseEntity<ResponseSchema> edit(@PathVariable Long id, @Valid @RequestBody PreRegistrationSchema preRegistrationSchema) {
        try {
            preRegistrationSchema = preRegistrationSchema.merge(preRegistrationSchema, this.preRegistrationService.findOne(id));
            if (preRegistrationSchema.getMcCodeId() != null)
                preRegistrationSchema.setMcCode(mcCodeService.findById(preRegistrationSchema.getMcCodeId()));

            preRegistrationSchema.setOp("Updated");
            preRegistrationSchema.setAt(LocalDateTime.now());
            preRegistrationSchema = this.preRegistrationService.save(preRegistrationSchema);
            return ResponseEntity.ok(new ResponseSchema("Success", "Pre Registration successfully Updated", preRegistrationSchema));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), preRegistrationSchema));
        }
    }

    @DeleteMapping("/remove/{id}")
    @Operation(summary = "Removes a Pre Registration")
    public ResponseEntity<ResponseSchema> remove(@PathVariable Long id) {
        try {
            PreRegistrationSchema preRegistrationSchema = this.preRegistrationService.delete(id);
            return ResponseEntity.ok(new ResponseSchema("Success", "Pre Registration successfully Removed", preRegistrationSchema));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), null));
        }
    }

    @GetMapping("/show/{id}")
    @Operation(summary = "Show Pre Registration of passed id")
    public ResponseEntity<ResponseSchema> show(@PathVariable Long id) {
        try {
            PreRegistrationSchema preRegistrationSchema = this.preRegistrationService.findOne(id);
            return ResponseEntity.ok(new ResponseSchema("Success", "Pre Registration successfully Retrieved", preRegistrationSchema));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), null));
        }
    }
}
