package ada.cielo.prospects.controllers;

import ada.cielo.prospects.schemas.MCCode;
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

    @GetMapping("/")
    @Operation(summary = "List of MCCs")
    public ResponseEntity<List<MCCode>> index(@RequestParam(required = false) String condition) {
        return ResponseEntity.ok(null);
    }
}
