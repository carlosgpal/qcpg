package com.qcpg.qcpg.service.qiskit;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Service for converting Qiskit Python files to QASM files.
 * Uses an external Python script to extract QASM circuits from Qiskit code.
 */
@Service
public class QiskitToQasmConverter {

    private static final String PYTHON_INTERPRETER = "python"; // Python interpreter command.

    /**
     * Converts a Qiskit Python file into a list of QASM files.
     *
     * @param pyFile The Qiskit Python file to convert.
     * @return A list of QASM files extracted from the Python file.
     * @throws Exception If an error occurs during processing.
     */
    public List<MultipartFile> convertPyFileToQasmFiles(MultipartFile pyFile) throws Exception {
        // Create a temporary file for the uploaded Python file.
        File tempPy = File.createTempFile("./temp/qiskit_", ".py");
        pyFile.transferTo(tempPy);

        // Locate the Python script for extracting QASM.
        ClassPathResource cpr = new ClassPathResource("pythonCode/extract_qasm3.py");
        File scriptFile = cpr.getFile();

        // Configure the process to execute the Python script.
        ProcessBuilder pb = new ProcessBuilder(
                PYTHON_INTERPRETER,
                scriptFile.getAbsolutePath(),
                tempPy.getAbsolutePath()
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();

        List<MultipartFile> qasmParts = new ArrayList<>();
        try (Scanner sc = new Scanner(process.getInputStream())) {
            StringBuilder currentQasm = null;
            int circuitIndex = 0;

            // Read the Python script's output line by line.
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println("PYTHON-OUTPUT: " + line); // Log Python script output.

                // Detect the start of a new QASM circuit.
                if ("###QASM###".equals(line.trim())) {
                    if (currentQasm != null && currentQasm.length() > 0) {
                        // Save the completed QASM circuit to a MultipartFile.
                        MultipartFile qasmFile = createMockMultipartQasm(
                                currentQasm.toString(),
                                pyFile.getOriginalFilename(),
                                circuitIndex);
                        qasmParts.add(qasmFile);
                        circuitIndex++;
                    }
                    currentQasm = new StringBuilder();
                } else {
                    // Append lines to the current QASM circuit.
                    if (currentQasm != null) {
                        currentQasm.append(line).append("\n");
                    }
                }
            }

            // Save the last QASM circuit if it exists.
            if (currentQasm != null && currentQasm.length() > 0) {
                MultipartFile qasmFile = createMockMultipartQasm(
                        currentQasm.toString(),
                        pyFile.getOriginalFilename(),
                        circuitIndex);
                qasmParts.add(qasmFile);
            }

            // Wait for the process to complete and check its exit code.
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("extract_qasm3.py exited with code " + exitCode);
            }
        }

        // Clean up the temporary Python file.
        tempPy.delete();

        return qasmParts; // Return the list of generated QASM files.
    }

    /**
     * Creates a `MockMultipartFile` representing a QASM file.
     *
     * @param qasmContent  The content of the QASM file.
     * @param originalName The original name of the uploaded Python file.
     * @param index        The index of the QASM circuit within the file.
     * @return A MultipartFile representing the QASM circuit.
     */
    private MultipartFile createMockMultipartQasm(String qasmContent, String originalName, int index) {
        try {
            // Generate a safe filename by sanitizing the original filename.
            String safeName = (originalName == null)
                    ? "unknown"
                    : originalName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");

            String qasmFileName = safeName + "_circuit" + index + ".qasm";
            byte[] qasmBytes = qasmContent.getBytes();

            // Create and return a MockMultipartFile.
            return new MockMultipartFile(
                    "files",
                    qasmFileName,
                    "application/x-qasm", // QASM file content type.
                    qasmBytes
            );
        } catch (Exception e) {
            throw new RuntimeException("Error creating MockMultipartFile for QASM", e);
        }
    }
}
