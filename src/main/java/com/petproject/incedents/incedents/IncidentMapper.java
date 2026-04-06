package com.petproject.incedents.incedents;

import com.petproject.incedents.incedents.dto.*;
import org.springframework.stereotype.Service;

@Service
public class IncidentMapper {

    public IncidentResponse toIncidentResponse(Incident incident, Boolean created) {
        return IncidentResponse.builder()
                .message(incident.getMessage())
                .coordinate(incident.getCoordinate())
                .status(incident.getStatus())
                .isCreated(created)
                .build();
    }

    public Incident toIncident(IncidentRequest request) {
        return Incident.builder()
                .message(request.message())
                .coordinate(request.coordinate())
                .status(request.status())
                .build();
    }

    public void updateIncident(Incident incident, IncidentRequest request) {
        if (!request.message().equals(incident.getMessage())) {
            incident.setMessage(request.message());
        }
        if (!request.coordinate().equals(incident.getCoordinate())) {
            incident.setCoordinate(request.coordinate());
        }
        if (request.status() != incident.getStatus()) {
            incident.setStatus(request.status());
        }
    }

    public void updateIncidentStatus(Incident incident, IncidentRequestStatus request) {
        if (request.status() != incident.getStatus()) {
            incident.setStatus(request.status());
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
