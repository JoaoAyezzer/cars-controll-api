package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.User;
import br.edu.unicesumar.carscontrollapi.dto.UserCreate;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User toEntity(UserCreate userCreate) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return User
                .builder()
                .active(true)
                .email(userCreate.email())
                .password(encoder.encode(userCreate.password()))
                .build();

    }

    public static void validateDTO(UserCreate userCreate) {
        if(Objects.isNull(userCreate.name())) throw new DataIntegrityException("Nome do usuario obrigatório");
        if (Objects.isNull(userCreate.email())) throw new DataIntegrityException("O email do usuario é obrigatorio");
        if (Objects.isNull(userCreate.password())) throw new DataIntegrityException("A senha do usuario é Obrigatorio");

    }
}
