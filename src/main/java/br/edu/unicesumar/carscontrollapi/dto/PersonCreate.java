package br.edu.unicesumar.carscontrollapi.dto;

import java.time.LocalDate;

public record PersonCreate(
        String firstName,
        String lastName,
        String cpf,
        String cnh,
        LocalDate expirationDateCnh,
        String phone,
        AddressCreate address
) {
}
