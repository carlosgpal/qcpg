package com.qcpg.qcpg.analysis;

import com.qcpg.qcpg.entities.QuantumProgram;

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

        return new QuantumProgram();
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
}