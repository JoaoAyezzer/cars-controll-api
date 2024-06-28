package br.edu.unicesumar.carscontrollapi.mapper;

import br.edu.unicesumar.carscontrollapi.domain.User;
import br.edu.unicesumar.carscontrollapi.dto.UserCreate;
import br.edu.unicesumar.carscontrollapi.dto.UserDTO;
import br.edu.unicesumar.carscontrollapi.dto.UserUpdateDTO;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import org.springframework.beans.BeanUtils;
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
    public static User toEntity(UserUpdateDTO updateDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        BeanUtils.copyProperties(updateDTO, user);
        user.setPassword(encoder.encode(user.getPassword()));
        return user;
    }
    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }


}
