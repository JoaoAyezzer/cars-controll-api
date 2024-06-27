package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.Client;
import br.edu.unicesumar.carscontrollapi.dto.ClientCreateDTO;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import br.edu.unicesumar.carscontrollapi.exceptions.ObjectNotfoundException;
import br.edu.unicesumar.carscontrollapi.mapper.ClientMapper;
import br.edu.unicesumar.carscontrollapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public Client save(ClientCreateDTO clientCreateDTO) {
        try {
            return clientRepository.save(ClientMapper.toEntity(clientCreateDTO));
        }catch (Exception e){
            throw new DataIntegrityException("Ops! Houve erro ao cadastrar novo cliente");
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
