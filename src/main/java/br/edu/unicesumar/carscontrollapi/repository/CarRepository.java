package br.edu.unicesumar.carscontrollapi.repository;

import br.edu.unicesumar.carscontrollapi.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
}
