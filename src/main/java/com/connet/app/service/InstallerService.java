package com.connet.app.service;

import com.connet.app.integration.installer.v1.dto.InstallerDTO;
import com.connet.app.integration.installer.v1.InstallerIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InstallerService {

    private InstallerIntegration installerIntegration;

    @Autowired
    public InstallerService(InstallerIntegration installerIntegration) {
        this.installerIntegration = installerIntegration;
    }

    public InstallerDTO getInstaller(UUID id) {
        return installerIntegration.getInstaller(id);
    }

    public String createInstaller(Long id) {
        return installerIntegration.createInstaller(id);
    }

    public InstallerDTO login(Long id, String password) {
        return installerIntegration.login(id, password);
    }

    public UUID update(UUID id, InstallerDTO installerDTO) {
        return installerIntegration.updateInstaller(id, installerDTO);
    }

    public InstallerDTO getInstallerExtern(Long id) {
        return installerIntegration.getInstallerExtern(id);
    }
}
