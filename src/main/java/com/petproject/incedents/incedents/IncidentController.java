package com.petproject.incedents.incedents;

import com.petproject.incedents.incedents.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
public class IncidentController {
    private final IncidentService incidentService;

    @GetMapping("/incidents/{id}")
    public ResponseEntity<IncidentResponse> getIncident (@PathVariable Long id) {
        return ResponseEntity.ok(this.incidentService.getById(id));
    }

    @PostMapping("/incidents")
    public ResponseEntity<IncidentResponse> createIncident (@RequestBody @Valid IncidentRequest request) {
        IncidentResponse response = this.incidentService.create(request);
        if (response.isCreated()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/incidents/{id}")
    public ResponseEntity<IncidentResponse> updateIncident (@PathVariable Long id, @RequestBody @Valid IncidentRequest request) {
        return ResponseEntity.ok(this.incidentService.updateIncident(id, request));
    }

    @PatchMapping("/incidents/{id}/status")
    public ResponseEntity<IncidentResponse> patchIncidentStatus (@PathVariable Long id, @RequestBody @Valid IncidentRequestStatus request) {
        return ResponseEntity.ok(this.incidentService.updateStatusIncident(id, request));
    }

    @PatchMapping("/incidents/{id}/coordinate")
    public ResponseEntity<IncidentResponse> patchIncidentCoordinate (@PathVariable Long id, @RequestBody @Valid IncidentRequestCoordinate request) {
        return ResponseEntity.ok(this.incidentService.updateCoordinateIncident(id, request));
    }

    @PatchMapping("/incidents/{id}/message")
    public ResponseEntity<IncidentResponse> patchIncidentMessage (@PathVariable Long id, @RequestBody @Valid IncidentRequestMessage request) {
        return ResponseEntity.ok(this.incidentService.updateMessageIncident(id, request));
    }
}
