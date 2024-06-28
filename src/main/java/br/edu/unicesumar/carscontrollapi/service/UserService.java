package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.User;
import br.edu.unicesumar.carscontrollapi.dto.*;
import br.edu.unicesumar.carscontrollapi.exceptions.ObjectNotfoundException;
import br.edu.unicesumar.carscontrollapi.mapper.UserMapper;
import br.edu.unicesumar.carscontrollapi.repository.PersonRepository;
import br.edu.unicesumar.carscontrollapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PersonRepository personRepository;

    public User create(UserCreate userCreate) {
        var person = personRepository.findById(userCreate.personID()).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado pessoa com id: " + userCreate.personID())
        );
        var user = UserMapper.toEntity(userCreate);
        user.setPerson(person);
        user = repository.save(user);
        person.setUser(user);
        personRepository.save(person);
        return user;
    }
    public void update(UserUpdateDTO updateDTO){
        findById(updateDTO.id());
        var person = personRepository.findById(updateDTO.personID()).orElseThrow(
                () -> new ObjectNotfoundException("Pessoa nao encontrada")
        );
        var user = UserMapper.toEntity(updateDTO);
        user.setPerson(person);
        user = repository.save(user);
        person.setUser(user);
        personRepository.save(person);
    }
    public UserDTO findById(UUID id) {
        return UserMapper.toDTO(repository.findById(id).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado")
        ));
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error("UserService->delete: {}", e.getMessage());
            throw new ObjectNotfoundException("Ops! hove erro ao deletar o usuario");
        }
    }
}
