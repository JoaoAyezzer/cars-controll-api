package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.User;
import br.edu.unicesumar.carscontrollapi.dto.Forgot;
import br.edu.unicesumar.carscontrollapi.dto.NewPassWithForgotCode;
import br.edu.unicesumar.carscontrollapi.dto.UserCreate;
import br.edu.unicesumar.carscontrollapi.exceptions.ObjectNotfoundException;
import br.edu.unicesumar.carscontrollapi.mapper.UserMapper;
import br.edu.unicesumar.carscontrollapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User create(UserCreate userCreate) {
        return repository.save(UserMapper.toEntity(userCreate));
    }
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new ObjectNotfoundException("Nao encontrado")
        );
    }

    public void forgotPassword(Forgot forgot) {
        var user = this.findByEmail(forgot.email());
        user.setForgotCode(UtilService.generateRandomCode());
        repository.save(user);
    }

    public void createNewPassword(NewPassWithForgotCode dto) {
    }

    public void forgotCodeValidate(String forgotCode) {
    }
}
