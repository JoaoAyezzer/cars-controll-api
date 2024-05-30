package br.edu.unicesumar.carscontrollapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cleaning extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn
    @ManyToOne
    private Person createdBy;

    @JoinColumn
    @ManyToOne
    private Car car;

    private Boolean open;

    private String obs;
}
