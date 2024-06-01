package br.edu.unicesumar.carscontrollapi.dto;


import java.math.BigDecimal;

public record CarCreate(
        String brand,
        String model,
        String color,
        Integer year,
        BigDecimal mileage,
        String identification
) {
}
