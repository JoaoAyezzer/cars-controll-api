package br.edu.unicesumar.carscontrollapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressCreateDTO {
    private String street;
    private String district;
    private String city;
    private String state;
    private String zip;
    private String country;
}
