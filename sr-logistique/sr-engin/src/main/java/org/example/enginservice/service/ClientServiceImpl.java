package org.example.enginservice.service;


import org.example.enginservice.dto.ClientDTO;
import org.example.enginservice.entities.Client;
import org.example.enginservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    private ClientDTO convertToDto(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setAddress(client.getAddress());
        dto.setEmail(client.getEmail());
        dto.setVille(client.getVille());
        dto.setFiscalId(client.getFiscalId());
        dto.setRc(client.getRc());
        dto.setCnss(client.getCnss());
        dto.setRib(client.getRib());
        dto.setGrantName(client.getGrantName());
        dto.setGerantTelephone(client.getGerantTelephone());
        dto.setGerantEmail(client.getGerantEmail());
        return dto;
    }

    private Client convertToEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setAddress(dto.getAddress());
        client.setEmail(dto.getEmail());
        client.setVille(dto.getVille());
        client.setFiscalId(dto.getFiscalId());
        client.setRc(dto.getRc());
        client.setCnss(dto.getCnss());
        client.setRib(dto.getRib());
        client.setGrantName(dto.getGrantName());
        client.setGerantTelephone(dto.getGerantTelephone());
        client.setGerantEmail(dto.getGerantEmail());
        return client;
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return convertToDto(savedClient);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            client.setName(clientDTO.getName());
            client.setAddress(clientDTO.getAddress());
            client.setEmail(clientDTO.getEmail());
            client.setVille(clientDTO.getVille());
            client.setFiscalId(clientDTO.getFiscalId());
            client.setRc(clientDTO.getRc());
            client.setCnss(clientDTO.getCnss());
            client.setRib(clientDTO.getRib());
            client.setGrantName(clientDTO.getGrantName());
            client.setGerantTelephone(clientDTO.getGerantTelephone());
            client.setGerantEmail(clientDTO.getGerantEmail());
            return convertToDto(clientRepository.save(client));
        }
        return null;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDTO> getClientById(Long id) {
        return clientRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
