package com.qcpg.qcpg.controller.graphCreation;

import com.qcpg.qcpg.service.qiskit.QiskitToQasmConverter;
import com.qcpg.qcpg.service.qasm.QasmIntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for processing Qiskit Python files.
 * Handles the conversion of Qiskit Python files into QASM files, which are then
 * processed
 * into a Code Property Graph (CPG) and integrated into Neo4j.
 */
@RestController
@RequestMapping("/api/qiskit")
public class QiskitController {

    private final QiskitToQasmConverter qiskitToQasmConverter;
    private final QasmIntegrationService qasmIntegrationService;

    /**
     * Constructor to initialize the controller with required dependencies.
     *
     * @param converter          Service for converting Qiskit Python files to QASM
     *                           files.
     * @param integrationService Service for processing QASM files into CPGs and
     *                           integrating with Neo4j.
     */
    public QiskitController(QiskitToQasmConverter converter,
            QasmIntegrationService integrationService) {
        this.qiskitToQasmConverter = converter;
        this.qasmIntegrationService = integrationService;
    }

    /**
     * Endpoint for processing a single Qiskit Python file.
     * Converts the file into QASM, processes it into a CPG, and imports it into
     * Neo4j.
     *
     * @param pyFile The Qiskit Python file to process.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/process")
    public ResponseEntity<String> processSingleQiskitFile(@RequestParam("file") MultipartFile pyFile) {
        try {
            // Validate that the file is provided and not empty
            if (pyFile == null || pyFile.isEmpty()) {
                return ResponseEntity.badRequest().body("No file uploaded.");
            }

            // Convert the Python file into a list of QASM files
            List<MultipartFile> qasmList = qiskitToQasmConverter.convertPyFileToQasmFiles(pyFile);

            // Check if any QASM circuits were generated
            if (qasmList.isEmpty()) {
                return ResponseEntity.ok("No quantum circuits found in the .py file.");
            }

            // Process the generated QASM files
            MultipartFile[] qasmArray = qasmList.toArray(new MultipartFile[0]);
            qasmIntegrationService.processMultipleQasmFiles(qasmArray);

            return ResponseEntity.ok("Qiskit .py processed -> QASM -> CPG -> Neo4j done.");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing Qiskit file: " + e.getMessage());
        }
    }

    /**
     * Endpoint for processing multiple Qiskit Python files.
     * Converts the files into QASM, processes them into CPGs, and imports them into
     * Neo4j.
     *
     * @param pyFiles Array of Qiskit Python files to process.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/processMultiple")
    public ResponseEntity<String> processMultipleQiskitFiles(@RequestParam("files") MultipartFile[] pyFiles) {
        try {
            // Validate that the files are provided and not empty
            if (pyFiles == null || pyFiles.length == 0) {
                return ResponseEntity.badRequest().body("No files uploaded.");
            }

            // Convert each Python file into QASM files
            List<MultipartFile> allQasmParts = new ArrayList<>();
            for (MultipartFile pyFile : pyFiles) {
                if (!pyFile.isEmpty()) {
                    List<MultipartFile> qasmList = qiskitToQasmConverter.convertPyFileToQasmFiles(pyFile);
                    allQasmParts.addAll(qasmList);
                }
            }

            // Check if any QASM circuits were generated
            if (allQasmParts.isEmpty()) {
                return ResponseEntity.ok("No quantum circuits found in the provided Python files.");
            }

            // Process all generated QASM files
            MultipartFile[] qasmArray = allQasmParts.toArray(new MultipartFile[0]);
            qasmIntegrationService.processMultipleQasmFiles(qasmArray);

            return ResponseEntity.ok("All Qiskit .py processed -> QASM -> CPG -> Neo4j done.");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing multiple Qiskit files: " + e.getMessage());
        }
    }
}
