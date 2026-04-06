package com.petproject.incedents.incedents;

import com.petproject.incedents.exceptions.NotFoundResourceException;
import com.petproject.incedents.incedents.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public IncidentResponse getById(Long id) {
        Incident incident = incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));
        return this.incidentMapper.toIncidentResponse(incident, false);
    }

    public IncidentResponse create(IncidentRequest request) {
        Optional<Incident> byCoordinateAndMessage = this.incidentRepository.findByCoordinateAndMessage(request.coordinate(), request.message());
        IncidentResponse response;
        if (byCoordinateAndMessage.isEmpty()) {
            response = this.incidentMapper.toIncidentResponse(
                    this.incidentRepository.save(this.incidentMapper.toIncident(request)),
                    true
            );
        } else {
            response = this.incidentMapper.toIncidentResponse(byCoordinateAndMessage.get(), false);
        }

        return response;
    }

    public IncidentResponse updateIncident(Long id, IncidentRequest request) {
        Incident incident = this.incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));

        this.incidentMapper.updateIncident(incident, request);
        this.incidentRepository.save(incident);
        return this.incidentMapper.toIncidentResponse(incident, false);
    }

    public IncidentResponse updateStatusIncident(Long id, IncidentRequestStatus request) {
        Incident incident = this.incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));

        this.incidentMapper.updateIncidentStatus(incident, request);
        this.incidentRepository.save(incident);
        return this.incidentMapper.toIncidentResponse(incident, false);

    }

    public IncidentResponse updateCoordinateIncident(Long id, IncidentRequestCoordinate request) {
        Incident incident = this.incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));

        this.incidentMapper.updateCoordinate(incident, request);
        this.incidentRepository.save(incident);
        return this.incidentMapper.toIncidentResponse(incident, false);
    }

    public IncidentResponse updateMessageIncident(Long id, IncidentRequestMessage request) {
        Incident incident = this.incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));

        this.incidentMapper.updateMessage(incident, request);
        this.incidentRepository.save(incident);
        return this.incidentMapper.toIncidentResponse(incident, false);
    }
}
