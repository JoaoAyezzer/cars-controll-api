package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.Address;
import br.edu.unicesumar.carscontrollapi.domain.Person;
import br.edu.unicesumar.carscontrollapi.domain.User;
import br.edu.unicesumar.carscontrollapi.dto.PersonCreate;
import br.edu.unicesumar.carscontrollapi.dto.PersonDTO;
import br.edu.unicesumar.carscontrollapi.dto.UserDTO;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.UUID;

public class PersonMapper {
    private PersonMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Person toPersonEntity(PersonCreate personCreate) {
        Address address = new Address();
        Person person = new Person();
        if (Objects.nonNull(personCreate.address())) {
            BeanUtils.copyProperties(personCreate.address(), address);
            address.setId(UUID.randomUUID());
        }
        BeanUtils.copyProperties(personCreate, person);
        person.setAddress(address);
        return person;
    }

    public static PersonDTO toDTO(Person person){
        UserDTO user = new UserDTO();
        PersonDTO personDTO = new PersonDTO();
        if (Objects.nonNull(person.getUser())) {
            BeanUtils.copyProperties(person.getUser(), user);
        }
        BeanUtils.copyProperties(person, personDTO);
        personDTO.setUser(user);
        return personDTO;
    }
}
