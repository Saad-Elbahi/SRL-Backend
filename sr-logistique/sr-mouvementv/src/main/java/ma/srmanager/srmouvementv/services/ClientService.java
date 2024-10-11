package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.ClientDTO;
import ma.srmanager.srmouvementv.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    List<ClientDTO> getAllClients();
    Optional<ClientDTO> getClientById(Long id);
    void deleteClient(Long id);
}
