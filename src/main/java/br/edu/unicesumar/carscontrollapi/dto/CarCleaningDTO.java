package br.edu.unicesumar.carscontrollapi.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarCleaningDTO {

    private UUID id;
    private UUID carId;
    private String CarDescription;
    private UUID createdByID;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String obs;

}
