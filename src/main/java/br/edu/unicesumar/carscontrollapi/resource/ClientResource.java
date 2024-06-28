package br.edu.unicesumar.carscontrollapi.resource;

import br.edu.unicesumar.carscontrollapi.domain.Client;
import br.edu.unicesumar.carscontrollapi.dto.ClientCreateDTO;
import br.edu.unicesumar.carscontrollapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "client")
public class ClientResource {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ClientCreateDTO dto) {
        var client = clientService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Client client) {
        clientService.update(client);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clientService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Client> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

}
