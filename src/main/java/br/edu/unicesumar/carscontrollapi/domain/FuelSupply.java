package br.edu.unicesumar.carscontrollapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuelSupply extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate date;

    private BigDecimal mileage;

    private BigDecimal liters;

    private BigDecimal value;

    @JoinColumn
    @OneToOne
    private Ticket ticket;

    @JoinColumn
    @ManyToOne
    private Person createdBy;

    @JoinColumn
    @ManyToOne
    private Car car;


}
