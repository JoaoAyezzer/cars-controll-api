package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.Client;
import br.edu.unicesumar.carscontrollapi.dto.ClientCreateDTO;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import br.edu.unicesumar.carscontrollapi.exceptions.ObjectNotfoundException;
import br.edu.unicesumar.carscontrollapi.mapper.ClientMapper;
import br.edu.unicesumar.carscontrollapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public Client save(ClientCreateDTO clientCreateDTO) {
        try {
            return clientRepository.save(ClientMapper.toEntity(clientCreateDTO));
        }catch (Exception e){
            log.error("ClientService->save: {}", e.getMessage());
            throw new DataIntegrityException("Ops! Houve erro ao cadastrar novo cliente");
        }
    }
    public void update(Client client){
        findById(client.getId());
        try {
            clientRepository.save(client);
        }catch (Exception e){
            log.error("ClientService->update: {}", e.getMessage());
            throw new DataIntegrityException("Ops! Houve erro ao atualizar o cliente");
        }
    }
    public void delete(UUID id) {
        try {
            clientRepository.deleteById(id);
        }catch (Exception e){
            log.error("ClientService->delete: {}", e.getMessage());
            throw new DataIntegrityException("Ops! Houve erro ao deletar um cliente");
        }
    }
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    public Client findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ObjectNotfoundException("Ops! Nao encontramos cliente com id: " +  id)
        );
    }
}
