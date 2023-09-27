package ada.cielo.prospects.controllers;

import ada.cielo.prospects.model.schemas.ProspectsQueueSchema;
import ada.cielo.prospects.services.ProspectsQueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/v1/prospects")
@Tag(name = "Prospect Queue", description = "Endpoints for managing the prospects queue")
public class ProspectsQueueController {

    private final ProspectsQueueService prospectsQueueService;

    public ProspectsQueueController(ProspectsQueueService prospectsQueueService) {
        this.prospectsQueueService = prospectsQueueService;
    }

    @GetMapping("/")
    @Operation(summary = "List of pre-registrations in the queue")
    public ResponseEntity<List<ProspectsQueueSchema>> index(@RequestParam(required = false) String search) {
        List<ProspectsQueueSchema> prospectsQueueList = this.prospectsQueueService.findAll();
        return ResponseEntity.ok(prospectsQueueList);
    }

    @GetMapping("/next")
    @Operation(summary = "Get the next prospect in the queue")
    public ResponseEntity<ProspectsQueueSchema> next() {
        ProspectsQueueSchema prospectsQueueSchema = this.prospectsQueueService.getNext();
        return ResponseEntity.ok(prospectsQueueSchema);
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
