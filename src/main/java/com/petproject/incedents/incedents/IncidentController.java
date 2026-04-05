package com.petproject.incedents.incedents;

import com.petproject.incedents.incedents.dto.IncidentRequest;
import com.petproject.incedents.incedents.dto.IncidentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
public class IncidentController {
    private final IncidentService incidentService;

    @GetMapping("/incidents/{id}")
    public ResponseEntity<IncidentResponse> getIncident (@PathVariable Long id) {
        return this.incidentService.getById(id);
    }

    @PostMapping("/incidents")
    public ResponseEntity<IncidentResponse> createIncident (@RequestBody IncidentRequest request) {
        return this.incidentService.create(request);
    }

    @PutMapping("/incidents/{id}")
    public ResponseEntity<IncidentResponse> updateIncident (@PathVariable Long id, @RequestBody IncidentRequest request) {
        return this.incidentService.updateById(id, request);
    }

    @PatchMapping("/incidents/{id}/status")
    public ResponseEntity<IncidentResponse> patchIncident (@PathVariable Long id, @RequestBody IncidentRequest request) {
        return this.incidentService.updateStatus(id, request);
    }
}
