package br.edu.unicesumar.carscontrollapi.dto;

import java.util.UUID;

public record CarCleaningCreate(
        UUID createdBy,
        UUID car,
        String obs
) {
}
