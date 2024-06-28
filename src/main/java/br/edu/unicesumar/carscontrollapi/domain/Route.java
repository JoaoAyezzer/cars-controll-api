package br.edu.unicesumar.carscontrollapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Route extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn
    @ManyToOne
    private Client destination;

    @JoinColumn
    @ManyToOne
    private Person createdBy;

    private LocalDateTime startDateTime;

    private BigDecimal startMileage;

    private LocalDateTime endDateTime;

    private BigDecimal endMileage;

    @JoinColumn
    @ManyToOne
    private Car car;

}
