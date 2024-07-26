package com.qcpg.qcpg.analysis;

import com.qcpg.qcpg.entities.GenericNode;
import com.qcpg.qcpg.entities.QuantumProgram;
import com.qcpg.qcpg.enumerations.NodeTags;
import com.qcpg.qcpg.antlr4.qasm3Lexer;
import com.qcpg.qcpg.antlr4.qasm3Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Component;

@Component
public class QASMParser {

    public QuantumProgram parse(String filePath) throws IOException {
        if (!isValidQASMCode(filePath)) {
            throw new IllegalArgumentException("Syntax errors found in OpenQASM file.");
        }

        QuantumProgram quantumProgram = initiazeQuantumProgram();

        List<String> lines = readLinesFromFile(filePath);

        quantumProgram = parseQubits(quantumProgram, lines);
        // quantumProgram = parseClassicBits(quantumProgram, lines);
        // quantumProgram = parseGates(quantumProgram, lines);
        // quantumProgram = parseMeasures(quantumProgram, lines);

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
        }
        return lines;
    }

    private QuantumProgram parseQubits(QuantumProgram quantumProgram, List<String> lines) {
        List<GenericNode> qubits = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.startsWith("qreg")) {
                throw new IllegalArgumentException(
                        "Syntax errors found in OpenQASM , qreg will not be supported for OPENQASM 3.0 in the future.");
            }

            if (line.startsWith("qubit")) {
                String[] tokens = line.split(" ");
                String qDeclaration = tokens[0];
                String qSet = tokens[1].replace(";", "");
                if (qDeclaration.contains("[")) {
                    int startIndex = qDeclaration.indexOf("[") + 1;
                    int endIndex = qDeclaration.indexOf("]");
                    int numQubits = Integer.parseInt(qDeclaration.substring(startIndex, endIndex));
                    for (int j = 0; j < numQubits; j++) {
                        GenericNode qubit = new GenericNode();
                        qubit.setType("Qubit" + j);
                        qubit.setFullName(qSet + "[" + j + "]");
                        qubit.setSet(qSet);
                        qubit.setSourceFile("sourceFile");
                        qubit.setLineOfCode(i);
                        qubit.setCodeLine(line);
                        qubit.setImplicit(false);
                        qubit.setInferred(false);
                        qubit.setStaticAccess(false);
                        labels.clear();
                        labels.add(NodeTags.Declaration.toString());
                        labels.add(NodeTags.QuantumBit.toString());
                        qubit.setLabels(labels);
                        qubits.add(qubit);
                    }
                } else {
                    GenericNode qubit = new GenericNode();
                    qubit.setType("Qubit 0");
                    qubit.setFullName(qSet + "[0]");
                    qubit.setSet(qSet);
                    qubit.setSourceFile("sourceFile");
                    qubit.setLineOfCode(i);
                    qubit.setCodeLine(line);
                    qubit.setImplicit(false);
                    qubit.setInferred(false);
                    qubit.setStaticAccess(false);
                    labels.clear();
                    labels.add(NodeTags.Declaration.toString());
                    labels.add(NodeTags.QuantumBit.toString());
                    qubit.setLabels(labels);
                    qubits.add(qubit);
                }
            }
        }
        quantumProgram.setQubits(qubits);
        return quantumProgram;

    }

    private QuantumProgram parseClassicBits(QuantumProgram quantumProgram, List<String> lines) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseClassicBits'");
    }

    private QuantumProgram parseGates(QuantumProgram quantumProgram, List<String> lines) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseGates'");
    }

    private QuantumProgram parseMeasures(QuantumProgram quantumProgram, List<String> lines) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseMeasures'");
    }

    public boolean isValidQASMCode(String filePath) throws IOException {
        String code = new String(Files.readAllBytes(Paths.get(filePath)));

        qasm3Lexer lexer = new qasm3Lexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        qasm3Parser parser = new qasm3Parser(tokens);

        @SuppressWarnings("unused")
        ParseTree tree = parser.program();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            return false;
        }
        return true;
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