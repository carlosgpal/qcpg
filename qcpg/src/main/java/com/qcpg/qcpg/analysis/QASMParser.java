package com.qcpg.qcpg.analysis;

import com.qcpg.qcpg.entities.GenericNode;
import com.qcpg.qcpg.entities.GenericRelationship;
import com.qcpg.qcpg.entities.QuantumProgram;
import com.qcpg.qcpg.enumerations.NodeTags;
import com.qcpg.qcpg.enumerations.RelationshipTags;
import com.qcpg.qcpg.antlr4.qasm3Lexer;
import com.qcpg.qcpg.antlr4.qasm3Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Component;

@Component
public class QASMParser {

    private String filePath;

    public QuantumProgram parse(String filePath) throws IOException {
        if (!isValidQASMCode(filePath)) {
            throw new IllegalArgumentException("Syntax errors found in OpenQASM file " + filePath);
        }

        QuantumProgram quantumProgram = initiazeQuantumProgram();

        boolean inBlockComment = false;

        List<String> lines = readLinesFromFile(filePath);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (inBlockComment) {
                if (line.contains("*/")) {
                    inBlockComment = false;
                } else {
                    continue;
                }
            }
            if (line.startsWith("/*")) {
                inBlockComment = true;
                if (line.contains("*/")) {
                    inBlockComment = false;
                } else {
                    continue;
                }
            }
            if (line.startsWith("//")) {
                continue;
            }

            if (line.startsWith("qubit") || line.startsWith("qreg")) {
                quantumProgram.getQubits().addAll(parseQubits(line, i + 1));
            } else if (line.startsWith("bit") || line.startsWith("creg")) {
                quantumProgram.getClassicBits().addAll(parseClassicBits(line, i + 1));
            } else if (line.startsWith("measure")) {
                quantumProgram.getMeasures().addAll(
                        parseMeasures(line, i + 1, quantumProgram.getQubits(), quantumProgram.getClassicBits()));
            }
        }

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

    private List<GenericNode> parseQubits(String line, int lineNumber) {
        List<GenericNode> qubits = new ArrayList<>();
        Pattern patternQreg = Pattern.compile("qreg\\s+(\\w+)\\[(\\d+)\\];");
        Pattern patternQubit = Pattern.compile("qubit\\[(\\d+)\\]\\s+(\\w+);|qubit\\s+(\\w+);");

        Matcher matcherQreg = patternQreg.matcher(line);
        Matcher matcherQubit = patternQubit.matcher(line);

        if (matcherQreg.find()) {
            String qSet = matcherQreg.group(1);
            int numQubits = Integer.parseInt(matcherQreg.group(2));

            for (int j = 0; j < numQubits; j++) {
                qubits.add(createQubitNode(line, lineNumber, qSet, j));
            }
        } else if (matcherQubit.find()) {
            int numQubits = matcherQubit.group(1) != null ? Integer.parseInt(matcherQubit.group(1)) : 1;
            String qSet = matcherQubit.group(2) != null ? matcherQubit.group(2) : matcherQubit.group(3);

            for (int j = 0; j < numQubits; j++) {
                qubits.add(createQubitNode(line, lineNumber, qSet, numQubits > 1 ? j : -1));
            }
        }
        return qubits;
    }

    private GenericNode createQubitNode(String line, int lineNumber, String qSet, int index) {
        GenericNode qubit = new GenericNode();
        qubit.setType("Qubit" + (index == -1 ? 0 : index));
        qubit.setFullName("Qubit " + qSet + "[" + (index == -1 ? 0 : index) + "]");
        qubit.setSet(qSet);
        qubit.setSourceFile(filePath);
        qubit.setLineOfCode(lineNumber);
        qubit.setCodeLine(line);
        qubit.setImplicit(false);
        qubit.setInferred(false);
        qubit.setStaticAccess(false);
        List<String> labels = new ArrayList<>();
        labels.add(NodeTags.Declaration.toString());
        labels.add(NodeTags.QuantumBit.toString());
        qubit.setLabels(labels);
        return qubit;
    }

    private List<GenericNode> parseClassicBits(String line, int lineNumber) {
        List<GenericNode> classicBits = new ArrayList<>();
        Pattern patternCreg = Pattern.compile("creg\\s+(\\w+)\\[(\\d+)\\];");
        Pattern patternBit = Pattern.compile("bit\\[(\\d+)\\]\\s+(\\w+);|bit\\s+(\\w+);");

        Matcher matcherCreg = patternCreg.matcher(line);
        Matcher matcherBit = patternBit.matcher(line);

        if (matcherCreg.find()) {
            String bSet = matcherCreg.group(1);
            int numBits = Integer.parseInt(matcherCreg.group(2));

            for (int j = 0; j < numBits; j++) {
                classicBits.add(createClassicBitNode(line, lineNumber, bSet, j));
            }
        } else if (matcherBit.find()) {
            int numBits = matcherBit.group(1) != null ? Integer.parseInt(matcherBit.group(1)) : 1;
            String bSet = matcherBit.group(2) != null ? matcherBit.group(2) : matcherBit.group(3);

            for (int j = 0; j < numBits; j++) {
                classicBits.add(createClassicBitNode(line, lineNumber, bSet, numBits > 1 ? j : -1));
            }
        }
        return classicBits;
    }

    private GenericNode createClassicBitNode(String line, int lineNumber, String bSet, int index) {
        GenericNode classicBit = new GenericNode();
        classicBit.setType("Bit" + (index == -1 ? 0 : index));
        classicBit.setFullName("Bit " + bSet + "[" + (index == -1 ? 0 : index) + "]");
        classicBit.setSet(bSet);
        classicBit.setSourceFile(filePath);
        classicBit.setLineOfCode(lineNumber);
        classicBit.setCodeLine(line);
        classicBit.setImplicit(false);
        classicBit.setInferred(false);
        classicBit.setStaticAccess(false);
        List<String> labels = new ArrayList<>();
        labels.add(NodeTags.Declaration.toString());
        labels.add(NodeTags.ClassicBit.toString());
        classicBit.setLabels(labels);
        return classicBit;
    }

    private List<GenericNode> parseMeasures(String line, int lineNumber, List<GenericNode> qubits,
            List<GenericNode> classicBits) {
        List<GenericNode> measures = new ArrayList<>();
        Pattern patternSingle = Pattern.compile("measure\\s+(\\w+\\[\\d+\\])\\s*->\\s*(\\w+\\[\\d+\\]);");
        Pattern patternRange = Pattern
                .compile("measure\\s+(\\w+)\\[(\\d+):(\\d+)\\]\\s*->\\s*(\\w+)\\[(\\d+):(\\d+)\\];");

        Matcher matcherSingle = patternSingle.matcher(line);
        Matcher matcherRange = patternRange.matcher(line);

        if (matcherSingle.find()) {
            String source = matcherSingle.group(1);
            String target = matcherSingle.group(2);

            GenericNode souceNode = findNodeByName(qubits, "Qubit " + source);
            GenericNode targetNode = new GenericNode();
            if (souceNode == null) {
                souceNode = findNodeByName(classicBits, "Bit " + source);
                targetNode = findNodeByName(qubits, "Qubit " + target);
            } else {
                targetNode = findNodeByName(classicBits, "Bit " + target);
            }

            measures.add(createMeasureNode(line, lineNumber, source, target, souceNode, targetNode));
        } else if (matcherRange.find()) {
            String sourceSet = matcherRange.group(1);
            int sourceStart = Integer.parseInt(matcherRange.group(2));
            int sourceEnd = Integer.parseInt(matcherRange.group(3));
            String targetSet = matcherRange.group(4);
            int targetStart = Integer.parseInt(matcherRange.group(5));
            int targetEnd = Integer.parseInt(matcherRange.group(6));

            for (int i = sourceStart, j = targetStart; i <= sourceEnd && j <= targetEnd; i++, j++) {
                String source = sourceSet + "[" + i + "]";
                String target = targetSet + "[" + j + "]";

                GenericNode souceNode = findNodeByName(qubits, "Qubit " + source);
                GenericNode targetNode = new GenericNode();
                if (souceNode == null) {
                    souceNode = findNodeByName(classicBits, "Bit " + source);
                    targetNode = findNodeByName(qubits, "Qubit " + target);
                } else {
                    targetNode = findNodeByName(classicBits, "Bit " + target);
                }

                measures.add(createMeasureNode(line, lineNumber, source, target, souceNode, targetNode));
            }
        }
        return measures;
    }

    private GenericNode createMeasureNode(String line, int lineNumber, String source, String target,
            GenericNode sourceNode, GenericNode targetNode) {
        GenericNode measure = new GenericNode();
        measure.setType("Measure");
        measure.setFullName("Measure " + source + "->" + target);
        measure.setSet(source);
        measure.setSourceFile(filePath);
        measure.setLineOfCode(lineNumber);
        measure.setCodeLine(line);
        measure.setImplicit(false);
        measure.setInferred(false);
        measure.setStaticAccess(false);
        List<String> labels = new ArrayList<>();
        labels.add(NodeTags.Declaration.toString());
        labels.add(NodeTags.Measure.toString());
        measure.setLabels(labels);

        if (sourceNode != null) {
            measure.getRelationshipsIn()
                    .add(new GenericRelationship(RelationshipTags.RELEVANT_FOR_MEASURES.toString(), sourceNode));
        }
        if (targetNode != null) {
            measure.getRelationshipsOut()
                    .add(new GenericRelationship(RelationshipTags.RELEVANT_FOR_MEASURES.toString(), targetNode));
        }

        return measure;
    }

    private GenericNode findNodeByName(List<GenericNode> nodes, String name) {
        for (GenericNode node : nodes) {
            if (node.getFullName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public boolean isValidQASMCode(String filePath) throws IOException {
        Path file = Paths.get(filePath);
        this.filePath = file.getFileName().toString();
        String code = new String(Files.readAllBytes(file));

        qasm3Lexer lexer = new qasm3Lexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        qasm3Parser parser = new qasm3Parser(tokens);

        @SuppressWarnings("unused")
        ParseTree tree = parser.program();

        return parser.getNumberOfSyntaxErrors() == 0;
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
