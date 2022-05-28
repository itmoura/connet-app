package com.connet.app.service;

import com.connet.app.integration.client.v1.ClientIntegration;
import com.connet.app.integration.client.v1.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {

    private ClientIntegration clientIntegration;

    @Autowired
    public ClientService(ClientIntegration clientIntegration) {
        this.clientIntegration = clientIntegration;
    }

    public ResponseEntity<?> createClient(ClientDTO clientDTO) {
        return clientIntegration.save(clientDTO);
    }

    public ClientDTO getClient(UUID id) {
        return clientIntegration.getClient(id);
    }

    public ClientDTO login(String email, String password) {
        return clientIntegration.login(email, password);
    }

    public void deleteClient(UUID id) {
        clientIntegration.deleteClient(id);
    }

    public UUID updateClient(UUID clientId, ClientDTO clientDTO) {
        return clientIntegration.update(clientId, clientDTO);
    }
}
