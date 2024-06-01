package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.Car;
import br.edu.unicesumar.carscontrollapi.dto.CarCreate;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class CarMapper {
    private CarMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Car toCarEntity(CarCreate dto){
        validateEntry(dto);
        Car car = new Car();
        BeanUtils.copyProperties(dto, car);
        car.setBlocked(false);
        return car;
    }

    private static void validateEntry(CarCreate dto){
        if (Objects.isNull(dto.brand())) throw new DataIntegrityException("Marca é obrigatório");
        if (dto.brand().isEmpty()) throw new DataIntegrityException("Marca nao pode ser uma string vazia");
        if (Objects.isNull(dto.model())) throw new DataIntegrityException("Modelo é obrigatório");
        if (dto.model().isEmpty()) throw new DataIntegrityException("Modelo nao pode ser uma string vazia");
        if (Objects.isNull(dto.color())) throw new DataIntegrityException("Cor é obrigatório");
        if (dto.color().isEmpty()) throw new DataIntegrityException("Cor nao pode ser uma string vazia");
        if (Objects.isNull(dto.identification())) throw new DataIntegrityException("A placa é obrigatório");
        if (dto.identification().isEmpty()) throw new DataIntegrityException("A placa nao pode ser uma string vazia");
        if (Objects.isNull(dto.mileage())) throw new DataIntegrityException("A quilometragem é obrigatoria");
        if (dto.mileage().compareTo(BigDecimal.ZERO) < 0 ) throw new DataIntegrityException("A quilometragem nao pode ser um valor negativo");
        if (Objects.isNull(dto.year())) throw new DataIntegrityException("O ano é obrigatorio");
        if (dto.year() > LocalDate.now().getYear()) throw new DataIntegrityException("O ano do veiculo nao pode maior que o ano atual");
    }
}
