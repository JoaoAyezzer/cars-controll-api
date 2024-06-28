package br.edu.unicesumar.carscontrollapi.dto;

import java.util.UUID;

public record CarDefectCreateDTO(
        UUID createdBy,
        UUID car,
        String description
) {
}
