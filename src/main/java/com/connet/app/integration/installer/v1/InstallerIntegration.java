package com.connet.app.integration.installer.v1;

import com.connet.app.integration.installer.v1.dto.InstallerDTO;
import org.hibernate.cfg.beanvalidation.IntegrationException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@FeignClient(name = "installer", url = "${api.installer.host}${api.installer.v1.basePath}")
public interface InstallerIntegration {

    @GetMapping(value = "${api.installer.v1.installers}/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    InstallerDTO getInstaller(@PathVariable("id") UUID id) throws IntegrationException;

    @GetMapping(value = "${api.installer.v1.installers}/extern/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    InstallerDTO getInstallerExtern(@PathVariable("id") Long id) throws IntegrationException;

    @GetMapping(value = "${api.installer.v1.installers}", produces = APPLICATION_JSON_UTF8_VALUE)
    List<InstallerDTO> getFullInstaller() throws IntegrationException;

    @GetMapping(value = "${api.installer.v1.installers}/intern/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    InstallerDTO getIdInstallerIntern(@PathVariable("id") Long id) throws IntegrationException;

    @PostMapping(value = "${api.installer.v1.installers}", produces = APPLICATION_JSON_UTF8_VALUE)
    String createInstaller(@RequestParam Long id) throws IntegrationException;

    @PostMapping(value = "${api.installer.v1.installers}/login", produces = APPLICATION_JSON_UTF8_VALUE)
    InstallerDTO login(@RequestParam Long id, @RequestParam String password) throws IntegrationException;

    @PutMapping(value = "${api.installer.v1.installers}/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    UUID updateInstaller(@PathVariable("id") UUID id, @RequestBody InstallerDTO installer) throws IntegrationException;
}
