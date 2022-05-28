package com.connet.app.resources.v1;

import com.connet.app.integration.client.v1.dto.ClientDTO;
import com.connet.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController("API para cadastro de clientes")
@RequestMapping("/api/connet/v1/client/clients")
public class ClientResource {

    private ClientService clientService;

    @Autowired
    public ClientResource(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> save(@RequestBody ClientDTO clientDTO){
        return clientService.createClient(clientDTO);
    }

    @PostMapping(value = "/login", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientDTO> login(@RequestParam String email, @RequestParam String password){
        return ResponseEntity.ok(clientService.login(email, password));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientDTO> getClient(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(clientService.getClient(id));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public void deleteCampaign(@PathVariable("id") UUID id) {
        clientService.deleteClient(id);
    }

    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UUID> updateCampaign(@PathVariable("id") UUID clientId, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.updateClient(clientId, clientDTO));
    }


}
