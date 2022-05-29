package com.connet.app.resources.v1;

import com.connet.app.model.dto.ProcessDTO;
import com.connet.app.model.enumeration.Status;
import com.connet.app.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@RestController("ProcessResource")
@RequestMapping("/api/process/v1")
public class ProcessResource {

    @Autowired
    private ProcessService processService;

    @PostMapping("/client/request")
    public ResponseEntity<ProcessDTO> requestInstaller(@RequestBody ProcessDTO processDTO) {
        return ResponseEntity.ok(processService.requestInstaller(processDTO));
    }

    @GetMapping("/installer/{installer_id}/notify")
    public ResponseEntity<List<ProcessDTO>> notify(@PathVariable("installer_id") UUID installerId) {
        return ResponseEntity.ok(processService.notify(installerId));
    }

    @PatchMapping("/{process_id}/updateStatus")
    public ResponseEntity<ProcessDTO> updateStatus(@PathVariable("process_id") UUID processId, @RequestParam Status status) {
        return ResponseEntity.ok(processService.updateStatus(processId, status));
    }

    @PatchMapping("/{process_id}/updateRating")
    public ResponseEntity<ProcessDTO> updateRating(@PathVariable("process_id") UUID processId, @RequestParam Float rating) {
        return ResponseEntity.ok(processService.updateRating(processId, rating));
    }

    @GetMapping("/processor/{processor_id}")
    public ResponseEntity<ProcessDTO> getProcess(@PathVariable("processor_id") UUID processId) {
        return ResponseEntity.ok(processService.getProcess(processId));
    }

    @GetMapping("/client/{client_id}")
    public ResponseEntity<List<ProcessDTO>> getClientProcess(@PathVariable("client_id") UUID clientId) {
        return ResponseEntity.ok(processService.getClientProcess(clientId));
    }

}
