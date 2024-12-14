package org.example.enginservice.service;



import org.example.enginservice.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    List<ClientDTO> getAllClients();
    Optional<ClientDTO> getClientById(Long id);
    void deleteClient(Long id);
}
