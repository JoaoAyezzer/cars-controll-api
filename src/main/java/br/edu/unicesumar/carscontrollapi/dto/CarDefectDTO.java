package br.edu.unicesumar.carscontrollapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDefectDTO{
        private UUID id;
        private UUID carID;
        private UUID createdBy;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

}
