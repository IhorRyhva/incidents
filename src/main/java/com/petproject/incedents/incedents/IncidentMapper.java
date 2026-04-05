package com.petproject.incedents.incedents;

import com.petproject.incedents.incedents.dto.IncidentRequest;
import com.petproject.incedents.incedents.dto.IncidentResponse;
import org.springframework.stereotype.Service;

@Service
public class IncidentMapper {

    public IncidentResponse toIncidentResponse(Incident incident) {
        return IncidentResponse.builder()
                .message(incident.getMessage())
                .coordinate(incident.getCoordinate())
                .status(incident.getStatus())
                .build();
    }

    public Incident toIncident(IncidentRequest request) {
        return Incident.builder()
                .message(request.message())
                .coordinate(request.coordinate())
                .status(request.status())
                .build();
    }

    public Incident updateIncident(Incident incident, IncidentRequest request) {
        if (request.message() != null && !request.message().isBlank()) {
            incident.setMessage(request.message());
        }
        if (request.coordinate() != null && !request.coordinate().isBlank()) {
            incident.setCoordinate(request.coordinate());
        }
        if (request.status() != null) {
            incident.setStatus(request.status());
        }
        return incident;
    }

    public Incident updateIncidentStatus(Incident incident, IncidentRequest request) {
        if (request.status() != null) {
            incident.setStatus(request.status());
        }
        return incident;
    }
}
