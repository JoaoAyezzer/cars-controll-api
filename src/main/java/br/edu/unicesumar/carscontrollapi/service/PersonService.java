package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.Person;
import br.edu.unicesumar.carscontrollapi.dto.PersonCreate;
import br.edu.unicesumar.carscontrollapi.dto.PersonDTO;
import br.edu.unicesumar.carscontrollapi.dto.PersonUpdateDTO;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import br.edu.unicesumar.carscontrollapi.exceptions.ObjectNotfoundException;
import br.edu.unicesumar.carscontrollapi.mapper.PersonMapper;
import br.edu.unicesumar.carscontrollapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public Person save(PersonCreate dto) {
        try {
            return repository.save(PersonMapper.toEntity(dto));
        }catch (Exception e){
            log.error("PersonService->save: {}", e.getMessage());
            throw new DataIntegrityException("Ops! Houve um erro ao salvar.");
        }
    }
    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error("PersonService->delete: {}", e.getMessage());
            throw new DataIntegrityException("Ops! Houve um erro ao deletar.");
        }
    }
    public void update(PersonUpdateDTO dto){
        findById(dto.getId());
        try {
            repository.save(PersonMapper.toEntity(dto));
        }catch (Exception e){
            log.error("PersonService->update: {}", e.getMessage());
            throw new DataIntegrityException("Ops! Houve um erro ao atualizar.");
        }
    }

    public List<PersonDTO> findAll() {
        return repository.findAll().stream().map(PersonMapper::toDTO).toList();
    }
    public PersonDTO findById(UUID id) {
        return PersonMapper.toDTO(repository.findById(id).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado")
        ));
    }

}
