package com.connet.app.repository;

import com.connet.app.model.dto.ProcessDTO;
import com.connet.app.model.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProcessRepository extends JpaRepository<Process, UUID> {

    List<Process> findALLByInstallerId(Long installerId);

    List<Process> findByClientId(UUID clientId);
}
