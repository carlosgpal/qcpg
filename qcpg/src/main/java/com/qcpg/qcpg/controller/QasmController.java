package com.qcpg.qcpg.controller;

import com.qcpg.qcpg.service.qasm.QasmIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/qasm")
public class QasmController {

    private final QasmIntegrationService integrationService;

    @Autowired
    public QasmController(QasmIntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processQasmFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded.");
        }

        try {
            String qasmContent = new String(file.getBytes());
            integrationService.processQasm(qasmContent, file.getOriginalFilename());
            return ResponseEntity.ok("QASM processed and graph imported en Neo4j.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing QASM: " + e.getMessage());
        }
    }
}
