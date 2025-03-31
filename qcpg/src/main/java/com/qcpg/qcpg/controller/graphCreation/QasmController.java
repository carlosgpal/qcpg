package com.qcpg.qcpg.controller.graphCreation;

import com.qcpg.qcpg.service.qasm.QasmIntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * QasmController handles the REST API endpoints for processing QASM files.
 * Provides functionality to process single or multiple QASM files, validate
 * their syntax,
 * and generate a Code Property Graph (CPG) for Neo4j integration.
 */
@RestController
@RequestMapping("/api/qasm")
public class QasmController {

    private final QasmIntegrationService integrationService;

    /**
     * Constructor to initialize the controller with required dependencies.
     *
     * @param integrationService The service handling QASM processing and CPG
     *                           generation.
     */
    public QasmController(QasmIntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    /**
     * Endpoint to process a single QASM file.
     * Validates file existence, processes its content, and imports the resulting
     * CPG into Neo4j.
     *
     * @param file The QASM file to process.
     * @return ResponseEntity with the result of the operation.
     */
    @PostMapping("/process")
    public ResponseEntity<String> processQasmFile(@RequestParam("file") MultipartFile file) {
        // Check if the file is empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded.");
        }
        try {
            // Read file content as string
            String qasmContent = new String(file.getBytes());

            // Process the QASM content using the integration service
            integrationService.processSingleQasmFile(qasmContent, file.getOriginalFilename());

            return ResponseEntity.ok("QASM processed and CPG imported into Neo4j.");
        } catch (Exception e) {
            e.printStackTrace(); // Log stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing QASM: " + e.getMessage());
        }
    }

    /**
     * Endpoint to process multiple QASM files in a single request.
     * Validates file list, processes each file, and imports their CPGs into Neo4j.
     *
     * @param files Array of QASM files to process.
     * @return ResponseEntity with the result of the operation.
     */
    @PostMapping("/processMultiple")
    public ResponseEntity<String> processMultipleQasmFiles(@RequestParam("files") MultipartFile[] files) {
        // Check if files are provided
        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body("No files uploaded.");
        }
        try {
            // Process each file through the integration service
            integrationService.processMultipleQasmFiles(files);

            return ResponseEntity.ok("All QASM files processed and CPGs imported into Neo4j.");
        } catch (Exception e) {
            e.printStackTrace(); // Log stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing QASM files: " + e.getMessage());
        }
    }
}
