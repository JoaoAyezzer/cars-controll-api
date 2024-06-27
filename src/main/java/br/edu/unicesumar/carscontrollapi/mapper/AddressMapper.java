package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.Address;
import br.edu.unicesumar.carscontrollapi.dto.AddressCreateDTO;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class AddressMapper {
    private AddressMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Address toEntity(AddressCreateDTO address) {
        Address addressEntity = new Address();
        BeanUtils.copyProperties(address, addressEntity);
        addressEntity.setId(UUID.randomUUID());
        return addressEntity;
    }
}
