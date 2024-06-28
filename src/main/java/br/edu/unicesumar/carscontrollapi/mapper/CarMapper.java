package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.*;
import br.edu.unicesumar.carscontrollapi.dto.*;
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
    public static CarCleaningDTO toCarCleaningDTO(CarCleaning carCleaning){
        CarCleaningDTO carCleaningDTO = new CarCleaningDTO();
        BeanUtils.copyProperties(carCleaning, carCleaningDTO);
        return carCleaningDTO;
    }
    public static CarDefect toCarDefectEntity(CarDefectCreateDTO dto){
        CarDefect carDefect = new CarDefect();
        BeanUtils.copyProperties(dto, carDefect);
        return carDefect;
    }
    public static CarDefectDTO toCarDefectDTO(CarDefect carDefect){
        CarDefectDTO carDefectDTO = new CarDefectDTO();
        BeanUtils.copyProperties(carDefect, carDefectDTO);
        carDefectDTO.setCarID(carDefect.getCar().getId());
        carDefectDTO.setCreatedBy(carDefect.getCreatedBy().getId());
        return carDefectDTO;
    }
    public static FuelSupply toFuelSupplyEntity(FuelSupplyCreateDTO dto){
        FuelSupply fuelSupply = new FuelSupply();
        BeanUtils.copyProperties(dto, fuelSupply);
        return fuelSupply;
    }
    public static FuelSupplyDTO toFuelSupplyDTO(FuelSupply fuelSupply){
        FuelSupplyDTO fuelSupplyDTO = new FuelSupplyDTO();
        BeanUtils.copyProperties(fuelSupply, fuelSupplyDTO);
        fuelSupplyDTO.setId(fuelSupply.getCar().getId());
        fuelSupplyDTO.setCreatedBy(fuelSupply.getCreatedBy().getId());
        return fuelSupplyDTO;
    }
    public static Route toRouteEntity(RouteInitDTO dto){
        Route route = new Route();
        BeanUtils.copyProperties(dto, route);
        return route;
    }
    public static RouteDTO toRouteDTO(Route route){
        RouteDTO routeDTO = new RouteDTO();
        BeanUtils.copyProperties(route, routeDTO);
        routeDTO.setCar(route.getCar().getId());
        routeDTO.setDestination(route.getDestination().getId());
        routeDTO.setCreatedBy(route.getCreatedBy().getId());
        return routeDTO;
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
