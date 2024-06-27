package br.edu.unicesumar.carscontrollapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateDTO {
    private String name;
    private String cpfCnpj;
    private String phone;
    private String email;
    private AddressCreateDTO addressCreateDTO;

}
