package br.edu.unicesumar.carscontrollapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.net.URL;
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
    private Image ticket;

    @JoinColumn
    @ManyToOne
    private Person createdBy;

    @JoinColumn
    @ManyToOne
    private Car car;


}
