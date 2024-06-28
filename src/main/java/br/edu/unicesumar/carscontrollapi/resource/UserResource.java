package br.edu.unicesumar.carscontrollapi.resource;

import br.edu.unicesumar.carscontrollapi.domain.User;
import br.edu.unicesumar.carscontrollapi.dto.UserCreate;
import br.edu.unicesumar.carscontrollapi.dto.UserDTO;
import br.edu.unicesumar.carscontrollapi.dto.UserUpdateDTO;
import br.edu.unicesumar.carscontrollapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserResource {
    private final UserService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserCreate dto){
        var user = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserUpdateDTO dto){
        service.update(dto);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> get(@PathVariable UUID id){
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody UUID id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }


}
