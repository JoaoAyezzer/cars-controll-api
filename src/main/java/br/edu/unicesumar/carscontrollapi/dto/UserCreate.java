package br.edu.unicesumar.carscontrollapi.dto;

import java.util.UUID;

public record UserCreate(
        String name,
        String email,
        String password,
        UUID personID
) {
}
