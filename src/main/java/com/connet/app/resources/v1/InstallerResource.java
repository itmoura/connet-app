package com.connet.app.resources.v1;

import com.connet.app.integration.installer.v1.dto.InstallerDTO;
import com.connet.app.service.InstallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController("API para cadastro de installadores")
@RequestMapping("/api/connet/v1/installer/installers")
public class InstallerResource {

    private InstallerService installerService;

    @Autowired
    public InstallerResource(InstallerService installerService){
        this.installerService = installerService;
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InstallerDTO> getClient(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(installerService.getInstaller(id));
    }

    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createInstaller(@RequestParam Long id) {;
        return ResponseEntity.ok(installerService.createInstaller(id));
    }

    @PostMapping(value = "/login", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InstallerDTO> getInstaller(@RequestParam Long id, @RequestParam String password) {
        return ResponseEntity.ok(installerService.login(id, password));
    }

}