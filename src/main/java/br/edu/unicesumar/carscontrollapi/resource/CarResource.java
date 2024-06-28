package br.edu.unicesumar.carscontrollapi.resource;

import br.edu.unicesumar.carscontrollapi.domain.Car;
import br.edu.unicesumar.carscontrollapi.dto.*;
import br.edu.unicesumar.carscontrollapi.service.CarService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    //Car defect
    @PostMapping(value = "defect")
    public ResponseEntity<Void> createCarDefect(@RequestBody CarDefectCreateDTO dto){
        var defect = service.createCarDefect(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/defect/{id}").buildAndExpand(defect.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping(value = "defect")
    public ResponseEntity<List<CarDefectDTO>> findAllDefect(){
        return ResponseEntity.ok(service.findAllCarDefectDTO());
    }
    @GetMapping(value = "defect/{id}")
    public ResponseEntity<CarDefectDTO> findDefectById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findCarDefectById(id));
    }

    //Fuel supply
    @PostMapping(value = "fuel")
    public ResponseEntity<Void> createFuelSupply(@RequestBody FuelSupplyCreateDTO dto, @RequestParam(name = "file", required = false) MultipartFile file){
        var fuelSupply = service.saveFuelSupply(dto, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/fuel/{id}").buildAndExpand(fuelSupply.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping(value = "fuel")
    public ResponseEntity<List<FuelSupplyDTO>> findAllFuelSupply(){
        return ResponseEntity.ok(service.findAllFuelSupply());
    }
    @GetMapping(value = "fuel/{id}")
    public ResponseEntity<FuelSupplyDTO> findFuelSupplyById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findFuelSupplyById(id));
    }
    @DeleteMapping(value = "fuel/{id}")
    public ResponseEntity<Void> deleteFuelSupply(@PathVariable UUID id){
        service.deleteFuelSupply(id);
        return ResponseEntity.accepted().build();
    }

    //Route
    @PostMapping(value = "route/init")
    public ResponseEntity<Void> initRoute(@RequestBody RouteInitDTO dto){
        var route = service.initRoute(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/route/{id}").buildAndExpand(route.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "route/finish")
    public ResponseEntity<Void> finishRoute(@RequestBody RouteFinishDTO dto){
        service.finishRoute(dto);
        return ResponseEntity.accepted().build();
    }
    @GetMapping(value = "route")
    public ResponseEntity<List<RouteDTO>> findAllRoute(){
        return ResponseEntity.ok(service.findAllRoute());
    }
    @GetMapping(value = "route/{id}")
    public ResponseEntity<RouteDTO> findRouteById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findRouteById(id));
    }
    @DeleteMapping(value = "route/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable UUID id){
        service.deleteRoute(id);
        return ResponseEntity.accepted().build();
    }
    

}
