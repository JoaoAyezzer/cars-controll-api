package br.edu.unicesumar.carscontrollapi.dto;

import java.util.UUID;

public record UserUpdateDTO(
        UUID id,
        String email,
        String password,
        Boolean active,
        String role,
        UUID personID
) {
}
