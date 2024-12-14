package org.example.enginservice.controller;



import org.example.enginservice.dto.ClientDTO;
import org.example.enginservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/clientApi")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/createClient")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO createdClient = clientService.createClient(clientDTO);
        return ResponseEntity.ok(createdClient);
    }

    @PutMapping("/updateClient")
    public ResponseEntity<ClientDTO> updateClient( @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.updateClient(clientDTO.getId(), clientDTO);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allClient")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/clientById")
    public ResponseEntity<ClientDTO> getClientById(@RequestBody ClientDTO clientDTO) {
        Optional<ClientDTO> client = clientService.getClientById(clientDTO.getId());
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteClient")
    public ResponseEntity<Void> deleteClient(@RequestBody ClientDTO clientDTO) {
        clientService.deleteClient(clientDTO.getId());
        return ResponseEntity.noContent().build();
    }

}
