package ada.cielo.prospects.controllers;

import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import ada.cielo.prospects.model.schemas.ProspectsQueueSchema;
import ada.cielo.prospects.model.schemas.ResponseSchema;
import ada.cielo.prospects.services.PreRegistrationService;
import ada.cielo.prospects.services.ProspectsQueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/prospects-queue")
@Tag(name = "Prospects Queue", description = "Endpoints for managing the prospects queue")
public class ProspectsQueueController {

    private final ProspectsQueueService prospectsQueueService;
    private final PreRegistrationService preRegistrationService;

    public ProspectsQueueController(ProspectsQueueService prospectsQueueService, PreRegistrationService preRegistrationService) {
        this.prospectsQueueService = prospectsQueueService;
        this.preRegistrationService = preRegistrationService;
    }

    @GetMapping("")
    @Operation(summary = "List of pre-registrations in the queue")
    public ResponseEntity<ResponseSchema> index(@RequestParam(required = false) String search_term) {
        try {
            List<ProspectsQueueSchema> prospectsQueueSchemaList = this.prospectsQueueService.findByTerm(search_term);
            return ResponseEntity.ok(new ResponseSchema("Success", "Listing Data", prospectsQueueSchemaList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), null));
        }
    }

    @GetMapping("/next")
    @Operation(summary = "Get the next prospect from the queue")
    public ResponseEntity<ResponseSchema> next() {
        try {
            ProspectsQueueSchema prospectsQueueSchema = this.prospectsQueueService.getNext();
            return ResponseEntity.ok(new ResponseSchema("Success", "Next Prospect", prospectsQueueSchema));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Add a pre-registration in the queue")
    public ResponseEntity<ResponseSchema> add(@Valid @RequestBody ProspectsQueueSchema prospectsQueueSchema) {
        try {
            prospectsQueueSchema.setPreRegistration(this.preRegistrationService.findOne(prospectsQueueSchema.getPreRegistrationId()));
            prospectsQueueSchema.setQueueingAt(LocalDateTime.now());

            prospectsQueueSchema = this.prospectsQueueService.save(prospectsQueueSchema);
            return ResponseEntity.ok(new ResponseSchema("Success", "New Pre Registration added in queue successfully", prospectsQueueSchema));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), prospectsQueueSchema));
        }
    }

    @DeleteMapping("/remove/{pre_registration_id}")
    @Operation(summary = "Removes a pre-registration from the queue")
    public ResponseEntity<ResponseSchema> remove(@PathVariable Long pre_registration_id) {
        try {
            ProspectsQueueSchema prospectsQueueSchema = this.prospectsQueueService.findByPreRegistrationId(pre_registration_id);
            prospectsQueueSchema = this.prospectsQueueService.delete(prospectsQueueSchema.getId());
            return ResponseEntity.ok(new ResponseSchema("Success", "Pre Registration successfully removed from queue", prospectsQueueSchema));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), null));
        }
    }
}
