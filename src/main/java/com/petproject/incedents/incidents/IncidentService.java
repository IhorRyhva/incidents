package com.petproject.incedents.incidents;

import com.petproject.incedents.exceptions.NotFoundResourceException;
import com.petproject.incedents.incidents.dto.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class IncidentService {
    private static final Integer NEEDED_COUNT = 3; /**TODO change name, add change data**/
    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public IncidentResponse getById(Long id) {
        Incident incident = incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));
        return this.incidentMapper.toIncidentResponse(incident, incident.getCountOfReport());
    }

    public IncidentResponse create(IncidentRequest request) {
        double longitude = request.coordinate().getLongitude();
        double latitude = request.coordinate().getLatitude();
        double radius = request.incidentType().getRadius();
        ArrayList<Incident> incidents = getByCoordinate(latitude, radius, longitude);
        if (incidents.isEmpty()) {
           return createIncident(request);
        } else {
            ArrayList<Incident> matched = new ArrayList<>();
            for (Incident incident: incidents) {
                if (incident.getIncidentType() == request.incidentType()) {
                    matched.add(incident);
                }
            }
            if (!matched.isEmpty()) {
                Incident mostMatched = getMostMatchedIncident(request, matched);
                if (mostMatched.getCountOfReport() >= NEEDED_COUNT) {
                    mostMatched.setStatus(Status.CONFIRMED);
                }
                incidentRepository.save(mostMatched);
                return incidentMapper.toIncidentResponse(mostMatched, mostMatched.getCountOfReport());
            }
        }
        return createIncident(request);
    }

    private @NonNull Incident getMostMatchedIncident(IncidentRequest request, ArrayList<Incident> matched) {
        Incident mostMatched = matched.getFirst();
        for (Incident incident: matched) {
            if (Math.abs(distation(request.coordinate(), mostMatched.getCoordinate())) > Math.abs(distation(request.coordinate(), incident.getCoordinate()))) {
                mostMatched = incident;
            }
        }
        mostMatched.setCountOfReport(mostMatched.getCountOfReport() + 1);
        return mostMatched;
    }

    private double distation(@NotNull Coordinate coordinate, @NotNull Coordinate coordinate1) {
        return Math.sqrt((Math.pow(coordinate.getLatitude()- coordinate1.getLatitude(), 2)) +
                (Math.pow(coordinate.getLongitude() - coordinate1.getLongitude(), 2)));
    }

    private ArrayList<Incident> getByCoordinate(double latitude, double radius, double longitude) {
        return this.incidentRepository.findByCoordinate(latitude - radius, latitude + radius,
                longitude - radius, longitude + radius);
    }

    private IncidentResponse createIncident(IncidentRequest request) {
        Incident incident = this.incidentMapper.toIncident(request);
        incident.setStatus(Status.REPORTED);
        incident.setCountOfReport(1);
        return this.incidentMapper.toIncidentResponse(
                this.incidentRepository.save(incident),
                incident.getCountOfReport()
        );
    }

    public IncidentResponse updateIncident(Long id, IncidentRequest request) {
        Incident incident = this.incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));

        this.incidentMapper.updateIncident(incident, request);
        this.incidentRepository.save(incident);
        return this.incidentMapper.toIncidentResponse(incident, incident.getCountOfReport());
    }

    public IncidentResponse updateIncidentType(Long id, IncidentRequestStatus request) {
        Incident incident = this.incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));

        this.incidentMapper.updateIncidentType(incident, request);
        this.incidentRepository.save(incident);
        return this.incidentMapper.toIncidentResponse(incident, incident.getCountOfReport());

    }

    public IncidentResponse updateCoordinateIncident(Long id, IncidentRequestCoordinate request) {
        Incident incident = this.incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));

        this.incidentMapper.updateCoordinate(incident, request);
        this.incidentRepository.save(incident);
        return this.incidentMapper.toIncidentResponse(incident, incident.getCountOfReport());
    }

    public IncidentResponse updateMessageIncident(Long id, IncidentRequestMessage request) {
        Incident incident = this.incidentRepository.findById(id).orElseThrow(() -> new NotFoundResourceException(id));

        this.incidentMapper.updateMessage(incident, request);
        this.incidentRepository.save(incident);
        return this.incidentMapper.toIncidentResponse(incident, incident.getCountOfReport());
    }
}
