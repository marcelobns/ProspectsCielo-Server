package ada.cielo.prospects.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/v1/prospects")
@Tag(name = "Prospect Queue", description = "Endpoints for managing the prospects queue")
public class ProspectsQueueController {

    @GetMapping("/")
    @Operation(summary = "List of pre-registrations in the queue")
    public String index(@RequestParam(required = false) String query) {
        return null;
    }

    @PostMapping("/add")
    @Operation(summary = "Add a pre-registration in the queue")
    public String add() {
        return null;
    }

    @PutMapping("/edit")
    @Operation(summary = "Repositions a pre-registration in the queue")
    public String edit(@RequestParam(required = true) BigInteger id) {
        return null;
    }

    @DeleteMapping("/remove")
    @Operation(summary = "Removes a pre-registration from the queue")
    public String remove(@RequestParam(required = true) BigInteger id) {
        return null;
    }
}
