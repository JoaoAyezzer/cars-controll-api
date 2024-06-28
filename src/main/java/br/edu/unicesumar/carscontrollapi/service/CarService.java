package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.*;
import br.edu.unicesumar.carscontrollapi.dto.*;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import br.edu.unicesumar.carscontrollapi.exceptions.ObjectNotfoundException;
import br.edu.unicesumar.carscontrollapi.mapper.CarCleaningMapper;
import br.edu.unicesumar.carscontrollapi.mapper.CarMapper;
import br.edu.unicesumar.carscontrollapi.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;
    private final CarCleaningRepository carCleaningRepository;
    private final PersonRepository personRepository;
    private final CarDefectRepository carDefectRepository;
    private final FuelSupplyRepository fuelSupplyRepository;
    private final TicketService ticketService;
    private final RouteRepository routeRepository;
    private ClientRepository clientRepository;
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
    public CarDefect createCarDefect(CarDefectCreateDTO dto){
        var person = personRepository.findById(dto.createdBy()).orElseThrow(
                () -> new ObjectNotfoundException("Pessoa nao encontrada")
        );
        var car = findById(dto.car());
        var carDefect = CarMapper.toCarDefectEntity(dto);
        carDefect.setCreatedBy(person);
        carDefect.setCar(car);
        return carDefectRepository.save(carDefect);
    }
    public List<CarDefectDTO> findAllCarDefectDTO(){
        return carDefectRepository.findAll().stream().map(CarMapper::toCarDefectDTO).toList();
    }
    public CarDefectDTO findCarDefectById(UUID id){
        return CarMapper.toCarDefectDTO(carDefectRepository.findById(id).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrada")
        ));
    }

    public CarCleaning createCarCleaning(CarCleaningCreate dto){
        CarCleaningMapper.validateEntry(dto);
        var car = this.findById(dto.car());
        var person = personRepository.findById(dto.createdBy()).orElseThrow(
                () -> new ObjectNotfoundException("Pessoa nao encontrada com id: " + dto.createdBy())
        );
        var cleaning = CarCleaning
                .builder()
                .car(car)
                .createdBy(person)
                .obs(dto.obs())
                .open(true)
                .build();
        try {
            return carCleaningRepository.save(cleaning);
        }catch (Exception e){
            log.error("CarService->createCarCleaning: {}", e.getMessage());
            throw new DataIntegrityException("Ops! Algo deu errado. Nao foi concluido a solicitacao de limpeza");
        }
    }

    public CarCleaningDTO getCarCleaning(UUID id) {
        return CarMapper.toCarCleaningDTO(carCleaningRepository.findById(id).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado")
        ));
    }

    public FuelSupply saveFuelSupply(FuelSupplyCreateDTO dto, MultipartFile file){
        FuelSupply fuelSupply = CarMapper.toFuelSupplyEntity(dto);
        Car car = this.findById(dto.car());
        Ticket ticket = null;
        Person person = personRepository.findById(dto.createdBy()).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado pessoa!")
        );
        fuelSupply.setCar(car);
        fuelSupply.setCreatedBy(person);
        if (Objects.nonNull(file)){
            ticket = ticketService.saveTicketImage(file);
        }
        fuelSupply.setTicket(ticket);
        return fuelSupplyRepository.save(fuelSupply);
    }
    public List<FuelSupplyDTO> findAllFuelSupply(){
        return fuelSupplyRepository.findAll().stream().map(CarMapper::toFuelSupplyDTO).toList();
    }
    public FuelSupplyDTO findFuelSupplyById(UUID id){
        return CarMapper.toFuelSupplyDTO(fuelSupplyRepository.findById(id).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado!")
        ));
    }
    public void  deleteFuelSupply(UUID id){
        try {
            fuelSupplyRepository.deleteById(id);
        }catch (Exception e){
            log.error("CarService->deleteFuelSupply: {}", e.getMessage());
            throw new DataIntegrityException("Ops! houve erro ao deletar");
        }
    }

    public Route initRoute(RouteInitDTO dto){
        Person person = personRepository.findById(dto.createdBy()).orElseThrow(
                () -> new ObjectNotfoundException("Pessoa nao encontrada")
        );
        Car car = findById(dto.car());
        Client client = clientRepository.findById(dto.destination()).orElseThrow(
                () -> new ObjectNotfoundException("Cliente nao encontrada")
        );
        Route route = CarMapper.toRouteEntity(dto);
        route.setCar(car);
        route.setCreatedBy(person);
        route.setDestination(client);
        return routeRepository.save(route);
    }
    public RouteDTO findRouteById(UUID id){
        return CarMapper.toRouteDTO(routeRepository.findById(id).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado!")
        ));
    }
    public List<RouteDTO> findAllRoute(){
        return routeRepository.findAll().stream().map(CarMapper::toRouteDTO).toList();
    }
    public void finishRoute(RouteFinishDTO dto){
        Route route = routeRepository.findById(dto.id()).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado!")
        );
        route.setEndMileage(dto.endMileage());
        route.setEndDateTime(dto.endDateTime());
        route.getCar().setMileage(dto.endMileage());
        repository.save(route.getCar());
        routeRepository.save(route);
    }
    public void deleteRoute(UUID id){
        try {
            routeRepository.deleteById(id);
        }catch (Exception e){
            log.error("CarService->deleteRoute: {}", e.getMessage());
            throw new DataIntegrityException("Ops! houve erro ao deletar");
        }
    }


}
