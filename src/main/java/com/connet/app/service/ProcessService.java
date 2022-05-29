package com.connet.app.service;

import com.connet.app.integration.client.v1.ClientIntegration;
import com.connet.app.integration.installer.v1.InstallerIntegration;
import com.connet.app.integration.installer.v1.dto.InstallerDTO;
import com.connet.app.model.dto.ProcessDTO;
import com.connet.app.model.entity.Process;
import com.connet.app.model.enumeration.Status;
import com.connet.app.repository.ProcessRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.connet.app.model.entity.Process.convert;
import static com.connet.app.model.enumeration.Status.PENDING;

@Service
@Log4j2
public class ProcessService {

    private final ProcessRepository processRepository;
    private final InstallerIntegration installerIntegration;
    private final ClientIntegration clientIntegration;

    public ProcessService(@Autowired ProcessRepository processRepository, @Autowired InstallerIntegration installerIntegration, @Autowired ClientIntegration clientIntegration) {
        this.processRepository = processRepository;
        this.installerIntegration = installerIntegration;
        this.clientIntegration = clientIntegration;
    }

    public List<ProcessDTO> notify(UUID installerId) {
        InstallerDTO installerDTO = installerIntegration.getInstaller(installerId);
        return processRepository.findALLByInstallerId(installerDTO.getId()).stream().map(ProcessDTO::convert).collect(java.util.stream.Collectors.toList());
    }

    public ProcessDTO requestInstaller(ProcessDTO processDTO) {
        InstallerDTO installerDTO = installerIntegration.getInstallerExtern(processDTO.getInstallerId());
        UUID clientId = clientIntegration.getClient(processDTO.getClientId()).getClientId();

        if (installerDTO == null || clientId == null) {
            log.error("Installer or Client not found");
            throw new RuntimeException("Installer or Client not found");
        }
        Process process = convert(processDTO);
        process.setStatus(PENDING);
        return ProcessDTO.convert(processRepository.save(process));

    }

    public ProcessDTO getProcess(UUID processId) {
        return ProcessDTO.convert(processRepository.findById(processId).orElseThrow(() -> new RuntimeException("Process not found")));
    }

    public ProcessDTO updateStatus(UUID processId, Status status) {
        Process process = processRepository.findById(processId).orElseThrow(() -> new RuntimeException("Process not found"));
        process.setStatus(status);
        return ProcessDTO.convert(processRepository.save(process));
    }

    public ProcessDTO updateRating(UUID processId, Float rating) {
        Process process = processRepository.findById(processId).orElseThrow(() -> new RuntimeException("Process not found"));
        InstallerDTO installerDTO = installerIntegration.getIdInstallerIntern(process.getInstallerId());
        int qtd = 2;
        if (installerDTO.getQtd() != null)
            qtd = installerDTO.getQtd() + 1;
        installerDTO.setQtd(qtd);
        installerDTO.setRating((installerDTO.getRating() + rating) / installerDTO.getQtd());
        installerIntegration.updateInstaller(installerDTO.getInternId(), installerDTO);
        process.setRating(rating);
        return ProcessDTO.convert(processRepository.save(process));
    }
}
