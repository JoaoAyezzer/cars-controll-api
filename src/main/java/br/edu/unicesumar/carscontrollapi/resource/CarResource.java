package br.edu.unicesumar.carscontrollapi.resource;

import br.edu.unicesumar.carscontrollapi.domain.Car;
import br.edu.unicesumar.carscontrollapi.dto.CarCleaningCreate;
import br.edu.unicesumar.carscontrollapi.dto.CarCleaningDTO;
import br.edu.unicesumar.carscontrollapi.dto.CarCreate;
import br.edu.unicesumar.carscontrollapi.service.CarService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("car")
@RequiredArgsConstructor
public class CarResource {
    private final CarService service;

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<Car> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping(value = "identification/{identification}")
    public ResponseEntity<Car> findByIdentification(@PathVariable String identification) {
        return ResponseEntity.ok(service.findByIdentification(identification));
    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CarCreate dto) {
        var car = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(car.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Car car){
        service.update(car);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    //Cleaning Car
    @PostMapping(value = "cleaning")
    public ResponseEntity<Void> createCleaning(@RequestBody CarCleaningCreate dto){
        var carCleaning = service.createCarCleaning(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/cleaning/{id}").buildAndExpand(carCleaning.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping(value = "cleaning/{id}")
    public ResponseEntity<CarCleaningDTO> findCleaningById(@PathVariable UUID id){
        return ResponseEntity.ok(service.getCarCleaning(id));
    }
    

}
