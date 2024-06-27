package br.edu.unicesumar.carscontrollapi.repository;

import br.edu.unicesumar.carscontrollapi.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Ticket, UUID> {
}
