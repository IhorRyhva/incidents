package com.petproject.incedents.incidents;

import com.petproject.incedents.incidents.dto.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    @Query("""
            select i from Incident i 
            where i.coordinate.latitude between :latitudeMin and :latitudeMax
            and i.coordinate.longitude between :longitudeMin and :longitudeMax
            """)
    ArrayList<Incident> findByCoordinate(@Param("latitudeMin") double latitudeMin, @Param("latitudeMax") double latitudeMax,
                                         @Param("longitudeMin") double longitudeMin, @Param("longitudeMax") double longitudeMax);
}
