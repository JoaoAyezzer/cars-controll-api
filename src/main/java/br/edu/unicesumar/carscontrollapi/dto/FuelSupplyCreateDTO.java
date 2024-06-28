package br.edu.unicesumar.carscontrollapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record FuelSupplyCreateDTO(
        LocalDate date,
        BigDecimal mileage,
        BigDecimal liters,
        BigDecimal value,
        UUID createdBy,
        UUID car

) {
}
