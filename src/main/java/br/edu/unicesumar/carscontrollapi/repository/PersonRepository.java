package br.edu.unicesumar.carscontrollapi.repository;

import br.edu.unicesumar.carscontrollapi.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
}
