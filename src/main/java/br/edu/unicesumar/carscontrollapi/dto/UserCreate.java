package br.edu.unicesumar.carscontrollapi.dto;

public record UserCreate(
        String name,
        String email,
        String password
) {
}
