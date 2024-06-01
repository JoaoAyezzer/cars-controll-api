package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.Address;
import br.edu.unicesumar.carscontrollapi.domain.Person;
import br.edu.unicesumar.carscontrollapi.domain.User;
import br.edu.unicesumar.carscontrollapi.dto.PersonCreate;
import br.edu.unicesumar.carscontrollapi.dto.PersonDTO;
import br.edu.unicesumar.carscontrollapi.dto.UserDTO;

import java.util.Objects;
import java.util.UUID;

public class PersonMapper {
    private PersonMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Person toPersonEntity(PersonCreate personCreate) {
        Address address = null;
        if (Objects.nonNull(personCreate.address())) {
            address = Address
                    .builder()
                    .id(UUID.randomUUID())
                    .street(personCreate.address().street())
                    .district(personCreate.address().district())
                    .city(personCreate.address().city())
                    .country(personCreate.address().country())
                    .state(personCreate.address().state())
                    .zip(personCreate.address().zip())
                    .build();
        }
        return Person
                .builder()
                .cnh(personCreate.cnh())
                .cpf(personCreate.cpf())
                .phone(personCreate.phone())
                .firstName(personCreate.firstName())
                .lastName(personCreate.lastName())
                .expirationDateCnh(personCreate.expirationDateCnh())
                .address(address)
                .build();
    }

    public static PersonDTO toDTO(Person person){
        UserDTO user = null;
        if (Objects.nonNull(person.getUser())) {
            user = UserDTO
                    .builder()
                    .id(person.getUser().getId())
                    .active(person.getUser().getActive())
                    .email(person.getUser().getEmail())
                    .role(person.getUser().getRole())
                    .build();

        }
        return PersonDTO
                .builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .phone(person.getPhone())
                .cnh(person.getCnh())
                .expirationDateCnh(person.getExpirationDateCnh())
                .address(person.getAddress())
                .user(user)
                .createdAt(person.getCreatedAt())
                .updatedAt(person.getUpdatedAt())
                .build();
    }
}
