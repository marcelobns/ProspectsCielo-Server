package ada.cielo.prospects.controllers;

import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import ada.cielo.prospects.services.PreRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
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
    public ResponseEntity<List<PreRegistrationSchema>>  index(@RequestParam(required = false) String condition) {
        List<PreRegistrationSchema> preRegistrationList = this.preRegistrationService.findAll();
        return ResponseEntity.ok(preRegistrationList);
    }

    @GetMapping("/show")
    @Operation(summary = "List Pre Registrations")
    public ResponseEntity<List<PreRegistrationSchema>>  show(@RequestParam(required = false) String condition) {
        return null;
    }

    @PostMapping("/add")
    @Operation(summary = "Add ..")
    public String add() {
        return null;
    }

    @PutMapping("/edit")
    @Operation(summary = "Edit")
    public String edit(@RequestParam(required = true) BigInteger id) {
        return null;
    }

    @DeleteMapping("/remove")
    @Operation(summary = "Removes a ")
    public String remove(@RequestParam(required = true) BigInteger id) {
        return null;
    }
}
