package ada.cielo.prospects.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/v1/pre-registrations")
@Tag(name = "Pre Registrations", description = "Endpoints for managing pre-registrations")
public class PreRegistrationController {

    @GetMapping("/")
    @Operation(summary = "List Pre Registrations")
    public String index(@RequestParam(required = false) String query) {
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
