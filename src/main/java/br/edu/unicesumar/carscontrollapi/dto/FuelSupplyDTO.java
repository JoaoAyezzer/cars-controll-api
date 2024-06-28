package br.edu.unicesumar.carscontrollapi.dto;

import br.edu.unicesumar.carscontrollapi.domain.Ticket;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuelSupplyDTO {
    private UUID id;
    private LocalDate date;
    private BigDecimal mileage;
    private BigDecimal liters;
    private BigDecimal value;
    private Ticket ticket;
    private UUID createdBy;
    private UUID car;
}
