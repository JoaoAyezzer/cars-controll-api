package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.CarCleaning;
import br.edu.unicesumar.carscontrollapi.dto.CarCleaningCreate;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;

import java.util.Objects;

public class CarCleaningMapper {
    private CarCleaningMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateEntry(CarCleaningCreate dto){
        if (Objects.isNull(dto.car())) throw new DataIntegrityException("O veiculo é obrigatorio");
        if (Objects.isNull(dto.createdBy())) throw new DataIntegrityException("A pessoa que esta criando é obrigatorio");
    }
}
