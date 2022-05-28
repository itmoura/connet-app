package com.connet.app.integration.client.v1;

import com.connet.app.integration.client.v1.dto.ClientDTO;
import org.hibernate.cfg.beanvalidation.IntegrationException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@FeignClient(name = "client", url = "${api.client.host}${api.client.v1.basePath}")
public interface ClientIntegration {

    @PostMapping(value = "${api.client.v1.clients}", produces = APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<?> save(@RequestBody ClientDTO clientDTO) throws IntegrationException;

    @PostMapping(value = "${api.client.v1.clients}", produces = APPLICATION_JSON_UTF8_VALUE)
    ClientDTO login(@RequestParam String email, @RequestParam String password) throws IntegrationException;

    @GetMapping(value = "${api.client.v1.clients}/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    ClientDTO getClient(@PathVariable("id") UUID clientId) throws IntegrationException;

    @DeleteMapping(value = "${api.client.v1.clients}/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    void deleteClient(@PathVariable("id") UUID clientId) throws IntegrationException;

    @PutMapping(value = "${api.client.v1.clients}/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    UUID update(@PathVariable("id") UUID clientId, @RequestBody ClientDTO clientDTO) throws IntegrationException;
}
