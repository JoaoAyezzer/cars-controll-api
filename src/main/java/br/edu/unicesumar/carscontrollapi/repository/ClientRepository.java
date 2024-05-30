package br.edu.unicesumar.carscontrollapi.repository;

import br.edu.unicesumar.carscontrollapi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
}
