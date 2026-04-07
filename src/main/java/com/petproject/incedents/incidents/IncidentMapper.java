package com.petproject.incedents.incidents;

import com.petproject.incedents.incidents.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IncidentMapper {

    public IncidentResponse toIncidentResponse(Incident incident, Boolean created) {
        return IncidentResponse.builder()
                .message(incident.getMessage())
                .coordinate(incident.getCoordinate())
                .incidentType(incident.getIncidentType())
                .isCreated(created)
                .createdAt(incident.getCreatedAt())
                .build();
    }

    public Incident toIncident(IncidentRequest request) {
        return Incident.builder()
                .message(request.message())
                .coordinate(request.coordinate())
                .incidentType(request.incidentType())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void updateIncident(Incident incident, IncidentRequest request) {
        if (!request.message().equals(incident.getMessage())) {
            incident.setMessage(request.message());
        }
        if (!request.coordinate().equals(incident.getCoordinate())) {
            incident.setCoordinate(request.coordinate());
        }
        if (request.incidentType() != incident.getIncidentType()) {
            incident.setIncidentType(request.incidentType());
        }
    }

    public void updateIncidentType(Incident incident, IncidentRequestStatus request) {
        if (request.incidentType() != incident.getIncidentType()) {
            incident.setIncidentType(request.incidentType());
        }
    }

    public void updateCoordinate(Incident incident, IncidentRequestCoordinate request) {
        if (!request.coordinate().equals(incident.getCoordinate())) {
            incident.setCoordinate(request.coordinate());
        }
    }


    public void updateMessage(Incident incident, IncidentRequestMessage request) {
        if (!request.message().equals(incident.getMessage())) {
            incident.setMessage(request.message());
        }
    }
}
