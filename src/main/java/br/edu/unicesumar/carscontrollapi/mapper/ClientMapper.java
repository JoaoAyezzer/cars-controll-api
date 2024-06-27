package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.Client;
import br.edu.unicesumar.carscontrollapi.dto.ClientCreateDTO;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class ClientMapper {
    private ClientMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Client toEntity(ClientCreateDTO clientCreateDTO){
        Client client = new Client();
        BeanUtils.copyProperties(clientCreateDTO, client);
        client.setAddress(AddressMapper.toEntity(clientCreateDTO.getAddressCreateDTO()));
        client.setId(UUID.randomUUID());
        return client;
    }


}
