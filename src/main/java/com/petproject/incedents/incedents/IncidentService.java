package com.petproject.incedents.incedents;

import com.petproject.incedents.incedents.dto.IncidentRequest;
import com.petproject.incedents.incedents.dto.IncidentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public ResponseEntity<IncidentResponse> getById(Long id) {
        Optional<Incident> incident = incidentRepository.findById(id);
        if (incident.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.incidentMapper.toIncidentResponse(incident.get()));
    }

    public ResponseEntity<IncidentResponse> create(IncidentRequest request) {
        Optional<Incident> byCoordinateAndMessage = this.incidentRepository.findByCoordinateAndMessage(request.coordinate(), request.message());
        IncidentResponse response;
        if (byCoordinateAndMessage.isEmpty()) {
            response = this.incidentMapper.toIncidentResponse(
                    this.incidentRepository.save(this.incidentMapper.toIncident(request))
            );
        } else {
            response = this.incidentMapper.toIncidentResponse(byCoordinateAndMessage.get());
        }

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<IncidentResponse> updateById(Long id, IncidentRequest request) {
        Optional<Incident> byId = this.incidentRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            Incident incident = byId.get();
            incident = this.incidentMapper.updateIncident(incident, request);
            this.incidentRepository.save(incident);
            return ResponseEntity.ok(this.incidentMapper.toIncidentResponse(incident));
        }
    }

    public ResponseEntity<IncidentResponse> updateStatus(Long id, IncidentRequest request) {
        Optional<Incident> byId = this.incidentRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            Incident incident = byId.get();
            incident = this.incidentMapper.updateIncidentStatus(incident, request);
            this.incidentRepository.save(incident);
            return ResponseEntity.ok(this.incidentMapper.toIncidentResponse(incident));
        }
    }
}
