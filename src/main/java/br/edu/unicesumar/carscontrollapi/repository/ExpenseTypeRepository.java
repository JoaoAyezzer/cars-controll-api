package br.edu.unicesumar.carscontrollapi.repository;

import br.edu.unicesumar.carscontrollapi.domain.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, UUID> {
}
