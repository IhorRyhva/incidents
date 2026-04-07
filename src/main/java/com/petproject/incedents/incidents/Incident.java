package com.petproject.incedents.incidents;

import com.petproject.incedents.incidents.dto.Coordinate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @NotNull
    private Coordinate coordinate;

    @NotEmpty
    private String message;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IncidentType incidentType;

    @NotNull
    private LocalDateTime createdAt;
}
