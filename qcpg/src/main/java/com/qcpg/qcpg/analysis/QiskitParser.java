package com.qcpg.qcpg.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.qcpg.qcpg.entities.QuantumProgram;
import org.springframework.stereotype.Component;

@Component
public class QiskitParser {

    public QuantumProgram parse(String filePath) {
        if (!isValidQiskitCode(filePath)) {
            throw new IllegalArgumentException("Syntax errors found in Python file.");
        }
        QuantumProgram quantumProgram = initiazeQuantumProgram();

        List<String> lines = readLinesFromFile(filePath);

        System.out.println(lines);

        return quantumProgram;
    }

    private List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
        return lines;
    }

    private String readFileContent(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    public boolean isValidQiskitCode(String filePath) {
        String code = readFileContent(filePath);
        if (!containsQiskitImports(code)) {
            throw new IllegalArgumentException("Invalid Qiskit code. Please provide a valid Qiskit file.");
        }
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", filePath);
            processBuilder.environment().put("PYTHONIOENCODING", "UTF-8");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return true;
            } else {
                System.err.println("Python script error: " + output);
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean containsQiskitImports(String code) {
        return code.lines().anyMatch(line -> line.contains("import qiskit") || line.contains("from qiskit"));
    }

    private QuantumProgram initiazeQuantumProgram() {
        QuantumProgram quantumProgram = new QuantumProgram();
        quantumProgram.setQubits(new ArrayList<>());
        quantumProgram.setClassicBits(new ArrayList<>());
        quantumProgram.setGates(new ArrayList<>());
        quantumProgram.setMeasures(new ArrayList<>());
        return quantumProgram;
    }
}
