package br.edu.unicesumar.carscontrollapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RouteInitDTO(
        UUID destination,
        UUID createdBy,
        UUID car,
        LocalDateTime startDateTime,
        BigDecimal startMileage

) {
}
