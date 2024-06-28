package br.edu.unicesumar.carscontrollapi.dto;

import br.edu.unicesumar.carscontrollapi.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String cnh;
    private LocalDate expirationDateCnh;
    private String phone;
    private Address address;
}
