package br.edu.unicesumar.carscontrollapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    private UUID id;
    private UUID createdBy;
    private UUID destination;
    private UUID car;
    private LocalDateTime startDateTime;
    private BigDecimal startMileage;
    private LocalDateTime endDateTime;
    private BigDecimal endMileage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
