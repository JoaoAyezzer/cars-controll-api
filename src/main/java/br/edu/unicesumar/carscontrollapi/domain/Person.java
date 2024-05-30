package br.edu.unicesumar.carscontrollapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Person extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String cnh;
    private LocalDate expirationDateCnh;
    private String phone;
    @JoinColumn
    @OneToOne
    private User user;
    @JoinColumn
    @OneToOne
    private Address address;
}
