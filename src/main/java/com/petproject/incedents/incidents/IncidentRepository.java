package com.petproject.incedents.incidents;

import com.petproject.incedents.incidents.dto.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Optional<Incident> findByCoordinateAndMessage(Coordinate coordinate, String message);
}
