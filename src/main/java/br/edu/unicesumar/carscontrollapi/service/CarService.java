package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.Car;
import br.edu.unicesumar.carscontrollapi.dto.CarCreate;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import br.edu.unicesumar.carscontrollapi.exceptions.ObjectNotfoundException;
import br.edu.unicesumar.carscontrollapi.mapper.CarMapper;
import br.edu.unicesumar.carscontrollapi.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public Car save(CarCreate dto) {
        try {
            return repository.save(CarMapper.toCarEntity(dto));
        }catch (ConstraintViolationException e){
            log.error("CarService->save: {}", e.getMessage());
            throw new DataIntegrityException("Ja existe um carro com essa placa");
        }
    }
    public void update(Car car){
        var carEntity = this.findById(car.getId());
        BeanUtils.copyProperties(car, carEntity);
        try {
           repository.save(carEntity);
        }catch (ConstraintViolationException e){
            log.error("CarService->update: {}", e.getMessage());
            throw new DataIntegrityException("Ja existe um carro com essa placa");
        }
    }
    public List<Car> findAll() {
        return repository.findAll();
    }
    public Car findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado veiculo com o id: " + id)
        );
    }
    public Car findByIdentification(String identification) {
        return repository.findByIdentification(identification).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado veiculo com a placa: " + identification)
        );
    }
    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error("CarService->delete: {}", e.getMessage());
            throw new DataIntegrityException("Ops! nao foi possivel excluir um carro. Verifique e tente novamente mais tarde");
        }
    }

}
