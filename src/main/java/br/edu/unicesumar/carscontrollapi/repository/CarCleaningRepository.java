package br.edu.unicesumar.carscontrollapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.carscontrollapi.domain.CarCleaning;

import java.util.UUID;

@Repository
public interface CarCleaningRepository extends JpaRepository<CarCleaning, UUID> {
}
