package br.edu.unicesumar.carscontrollapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RouteFinishDTO(
        UUID id,
        LocalDateTime endDateTime,
        BigDecimal endMileage
) {
}
