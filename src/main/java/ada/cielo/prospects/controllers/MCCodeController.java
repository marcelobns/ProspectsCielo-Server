package ada.cielo.prospects.controllers;

import ada.cielo.prospects.model.schemas.MCCodeSchema;
import ada.cielo.prospects.model.schemas.ResponseSchema;
import ada.cielo.prospects.services.MCCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/mc-codes")
@Tag(name = "Merchant Category Codes", description = "Endpoints for retrieving merchant category codes")
public class MCCodeController {

    private final MCCodeService mcCodeService;

    public MCCodeController(MCCodeService mcCodeService) {
        this.mcCodeService = mcCodeService;
    }

    @GetMapping("")
    @Operation(summary = "List of MCCs")
    public ResponseEntity<ResponseSchema> index(@RequestParam(required = false) String search_term) {
        try {
            List<MCCodeSchema> mcCodeList = this.mcCodeService.findByTerm(search_term);
            return ResponseEntity.ok(new ResponseSchema("Success", "Listing Data", mcCodeList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseSchema("Error", e.getMessage(), null));
        }
    }
}
