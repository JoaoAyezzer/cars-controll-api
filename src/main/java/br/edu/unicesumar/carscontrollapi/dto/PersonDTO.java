package br.edu.unicesumar.carscontrollapi.dto;

import br.edu.unicesumar.carscontrollapi.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String cnh;
    private LocalDate expirationDateCnh;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String phone;
    private Address address;
    private UserDTO user;
}
