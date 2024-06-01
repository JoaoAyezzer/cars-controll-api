package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.Person;
import br.edu.unicesumar.carscontrollapi.dto.PersonCreate;
import br.edu.unicesumar.carscontrollapi.dto.PersonDTO;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import br.edu.unicesumar.carscontrollapi.exceptions.ObjectNotfoundException;
import br.edu.unicesumar.carscontrollapi.mapper.PersonMapper;
import br.edu.unicesumar.carscontrollapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
    private static final Logger log = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository repository;

    public Person save(PersonCreate dto) {
        try {
            return repository.save(PersonMapper.toPersonEntity(dto));
        }catch (Exception e){
            log.error("PersonService->save: {}", e.getMessage());
            throw new DataIntegrityException("Ops! Houve um erro ao salvar.");
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
