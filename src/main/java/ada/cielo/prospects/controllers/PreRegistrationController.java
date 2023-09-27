package ada.cielo.prospects.controllers;

import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import ada.cielo.prospects.model.schemas.ResponseSchema;
import ada.cielo.prospects.services.PreRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/pre-registrations")
@Tag(name = "Pre Registrations", description = "Endpoints for managing pre-registrations")
public class PreRegistrationController {

    private final PreRegistrationService preRegistrationService;
    public PreRegistrationController(PreRegistrationService preRegistrationService) {
        this.preRegistrationService = preRegistrationService;
    }

    @GetMapping("/")
    @Operation(summary = "List Pre Registrations")
    public ResponseEntity<ResponseSchema> index(@RequestParam(required = false) String search_term) {
        try {
            List<PreRegistrationSchema> preRegistrationList = this.preRegistrationService.findByTerm(search_term);
            return ResponseEntity.ok(new ResponseSchema("Success", "Listing Data", preRegistrationList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new Pre Registration")
    public ResponseEntity<ResponseSchema> add(@Valid @RequestBody PreRegistrationSchema preRegistrationSchema) {
        try {
            preRegistrationSchema.setOp("Create");
            preRegistrationSchema.setAt(LocalDateTime.now());
            preRegistrationSchema.validate();
            preRegistrationSchema = this.preRegistrationService.save(preRegistrationSchema);
            return ResponseEntity.ok(new ResponseSchema("Success", "New Pre Registration created successfully", preRegistrationSchema));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), preRegistrationSchema));
        }
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Store the changes of a Pre Registration")
    public ResponseEntity<ResponseSchema> edit(@PathVariable Long id, @Valid @RequestBody PreRegistrationSchema preRegistrationSchema) {
        try {
            preRegistrationSchema.setId(id);
            preRegistrationSchema.setOp("Update");
            preRegistrationSchema.setAt(LocalDateTime.now());
            preRegistrationSchema.validate();
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
