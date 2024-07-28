package com.qcpg.qcpg.analysis;

import com.qcpg.qcpg.entities.GenericNode;
import com.qcpg.qcpg.entities.GenericRelationship;
import com.qcpg.qcpg.entities.QuantumProgram;
import com.qcpg.qcpg.enumerations.NodeTags;
import com.qcpg.qcpg.enumerations.RelationshipTags;
import com.qcpg.qcpg.antlr4.qasm3Lexer;
import com.qcpg.qcpg.antlr4.qasm3Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
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

        QuantumProgram quantumProgram = initializeQuantumProgram();
        Map<String, GenericNode> nodeMap = new HashMap<>();

        List<String> lines = readLinesFromFile(filePath);
        boolean inBlockComment = false;

        GenericNode rootASTNode = createASTRootNode(filePath);
        quantumProgram.getASTNodes().add(rootASTNode);

        Stack<GenericNode> nodeStack = new Stack<>();
        nodeStack.push(rootASTNode);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();

            if (line.isEmpty()) {
                continue;
            }

            if (line.contains("*/")) {
                inBlockComment = false;
                continue;
            }

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
                List<GenericNode> qubits = parseQubits(line, i + 1);
                quantumProgram.getQubits().addAll(qubits);
                qubits.forEach(qubit -> nodeMap.put(qubit.getFullName(), qubit));
            } else if (line.startsWith("bit") || line.startsWith("creg")) {
                List<GenericNode> classicBits = parseClassicBits(line, i + 1);
                quantumProgram.getClassicBits().addAll(classicBits);
                classicBits.forEach(bit -> nodeMap.put(bit.getFullName(), bit));
            } else if (line.startsWith("measure")) {
                List<GenericNode> measures = parseMeasures(line, i + 1, quantumProgram.getQubits(),
                        quantumProgram.getClassicBits());
                quantumProgram.getMeasures().addAll(measures);
                measures.forEach(measure -> nodeMap.put(measure.getFullName(), measure));
            } else if (isGateLine(line)) {
                List<GenericNode> gates = parseGates(line, i + 1, nodeMap);
                quantumProgram.getGates().addAll(gates);
                gates.forEach(gate -> nodeMap.put(gate.getFullName(), gate));
            }

            if (!line.startsWith("OPENQASM") && !line.startsWith("include")) {
                if (line.endsWith("{")) {
                    String[] tokens = line.split("\\s+|\\(");
                    GenericNode blockNode = createASTNode(line, i + 1, "Block " + tokens[0]);
                    blockNode.getLabels().add("Statement");
                    nodeStack.peek().getRelationshipsOut()
                            .add(new GenericRelationship(RelationshipTags.AST.toString(), blockNode));
                    nodeStack.push(blockNode);
                } else if (line.equals("}")) {
                    nodeStack.pop();
                } else {
                    List<GenericNode> astNodes = parseASTNodes(line, i + 1, nodeStack.peek());
                    quantumProgram.getASTNodes().addAll(astNodes);
                    astNodes.forEach(astNode -> nodeMap.put(astNode.getFullName(), astNode));
                }
            }
        }

        return quantumProgram;
    }

    private GenericNode createASTRootNode(String filePath) {
        GenericNode root = new GenericNode();
        root.setType("ASTRoot");
        root.setFullName("AST Root");
        root.setSourceFile(filePath);
        root.setLineOfCode(0);
        root.setCodeLine("");
        root.setImplicit(false);
        root.setInferred(false);
        root.setStaticAccess(false);
        List<String> labels = new ArrayList<>();
        labels.add(NodeTags.Declaration.toString());
        root.setLabels(labels);
        return root;
    }

    private List<GenericNode> parseASTNodes(String line, int lineNumber, GenericNode parentNode) {
        List<GenericNode> astNodes = new ArrayList<>();
        String[] tokens = line.split("\\s+|,|->");

        if (tokens.length > 0) {
            String rootNodeType = tokens[0].replace(";", "");
            GenericNode rootNode = createASTNode(line, lineNumber, rootNodeType);
            rootNode.getLabels().add("Statement");
            parentNode.getRelationshipsOut().add(new GenericRelationship(RelationshipTags.AST.toString(), rootNode));
            astNodes.add(rootNode);

            if (isControlStructure(rootNodeType)) {
                int conditionEndIndex = findConditionEndIndex(line);
                String condition = line.substring(line.indexOf(tokens[1]), conditionEndIndex).trim();
                String statements = line.substring(conditionEndIndex).trim().replace("{", "").replace("}", "");

                GenericNode conditionNode = createASTNode(condition, lineNumber, "Condition");
                conditionNode.getLabels().add("Condition");
                rootNode.getRelationshipsOut()
                        .add(new GenericRelationship(RelationshipTags.AST.toString(), conditionNode));
                astNodes.add(conditionNode);

                String[] conditionTokens = condition.split("\\s+|,|->");
                for (String token : conditionTokens) {
                    if (!token.isEmpty()) {
                        GenericNode conditionTokenNode = createASTNode(condition, lineNumber, token);
                        astNodes.add(conditionTokenNode);
                        conditionNode.getRelationshipsOut()
                                .add(new GenericRelationship(RelationshipTags.AST.toString(), conditionTokenNode));
                    }
                }

                if (!statements.isEmpty()) {
                    GenericNode statementNode = createASTNode(statements, lineNumber, "Statement");
                    statementNode.getLabels().add("Statement");
                    rootNode.getRelationshipsOut()
                            .add(new GenericRelationship(RelationshipTags.AST.toString(), statementNode));
                    astNodes.add(statementNode);

                    String[] statementTokens = statements.split("\\s+|,|->");
                    for (String token : statementTokens) {
                        if (!token.isEmpty()) {
                            GenericNode statementTokenNode = createASTNode(statements, lineNumber, token);
                            astNodes.add(statementTokenNode);
                            statementNode.getRelationshipsOut()
                                    .add(new GenericRelationship(RelationshipTags.AST.toString(), statementTokenNode));
                        }
                    }
                }
            } else {
                for (int i = 1; i < tokens.length; i++) {
                    String token = tokens[i].replace(";", "");
                    if (!token.isEmpty()) {
                        GenericNode astNode = createASTNode(line, lineNumber, token);
                        astNodes.add(astNode);
                        rootNode.getRelationshipsOut()
                                .add(new GenericRelationship(RelationshipTags.AST.toString(), astNode));
                    }
                }
            }
        }

        return astNodes;
    }

    private int findConditionEndIndex(String line) {
        int index = 0;
        int openBrackets = 0;
        for (char c : line.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            } else if (c == ')') {
                openBrackets--;
                if (openBrackets == 0) {
                    return index + 1;
                }
            }
            index++;
        }
        return line.length();
    }

    private boolean isControlStructure(String token) {
        return token.equals("for") || token.equals("while") || token.equals("if") || token.equals("gate");
    }

    private GenericNode createASTNode(String line, int lineNumber, String content) {
        GenericNode astNode = new GenericNode();
        astNode.setType(content);
        astNode.setFullName(content + " at line " + lineNumber);
        astNode.setSourceFile(filePath);
        astNode.setLineOfCode(lineNumber);
        astNode.setCodeLine(line);
        astNode.setImplicit(false);
        astNode.setInferred(false);
        astNode.setStaticAccess(false);
        List<String> labels = new ArrayList<>();
        labels.add(NodeTags.Declaration.toString());
        astNode.setLabels(labels);
        return astNode;
    }

    private List<String> readLinesFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + filePath, e);
        }
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

    private List<GenericNode> parseGates(String line, int lineNumber, Map<String, GenericNode> nodeMap) {
        List<GenericNode> gates = new ArrayList<>();
        Pattern patternGate = Pattern.compile("(\\w+)\\s+([^;]+);");

        Matcher matcherGate = patternGate.matcher(line);

        if (matcherGate.find()) {
            String gateType = matcherGate.group(1);
            String operandsStr = matcherGate.group(2);
            String[] operands = operandsStr.split(",");

            List<GenericNode> operandNodes = new ArrayList<>();
            for (String operand : operands) {
                operand = operand.trim();
                GenericNode node = nodeMap.get("Qubit " + operand);
                if (node == null) {
                    node = nodeMap.get("Bit " + operand);
                }
                if (node != null) {
                    operandNodes.add(node);
                }
            }

            gates.add(createGateNode(line, lineNumber, gateType, operandNodes));
        }
        return gates;
    }

    private GenericNode createGateNode(String line, int lineNumber, String gateType, List<GenericNode> operandNodes) {
        GenericNode gate = new GenericNode();
        gate.setType(gateType);
        StringBuilder fullNameBuilder = new StringBuilder(gateType + " Gate");
        if (!operandNodes.isEmpty()) {
            fullNameBuilder.append(" (");
            for (int i = 0; i < operandNodes.size(); i++) {
                if (i > 0) {
                    fullNameBuilder.append(", ");
                }
                fullNameBuilder.append(operandNodes.get(i).getFullName());
            }
            fullNameBuilder.append(")");
        }
        gate.setFullName(fullNameBuilder.toString());
        gate.setSet(gateType);
        gate.setSourceFile(filePath);
        gate.setLineOfCode(lineNumber);
        gate.setCodeLine(line);
        gate.setImplicit(false);
        gate.setInferred(false);
        gate.setStaticAccess(false);
        List<String> labels = new ArrayList<>();
        labels.add(NodeTags.Declaration.toString());
        labels.add(NodeTags.QuantumGate.toString());
        gate.setLabels(labels);

        if (gateType.equals("cx") && operandNodes.size() == 2) {
            gate.getRelationshipsOut()
                    .add(new GenericRelationship(RelationshipTags.QU_0.toString(), operandNodes.get(0)));
            gate.getRelationshipsOut()
                    .add(new GenericRelationship(RelationshipTags.QU_1.toString(), operandNodes.get(1)));
        } else {
            for (GenericNode operand : operandNodes) {
                if (operand != null) {
                    gate.getRelationshipsIn()
                            .add(new GenericRelationship(RelationshipTags.RELEVANT_FOR_GATES.toString(), operand));
                }
            }
        }

        return gate;
    }

    private boolean isGateLine(String line) {
        String[] knownGates = { "cx", "h", "x", "y", "z", "t", "tdg", "s", "sdg" };
        for (String gate : knownGates) {
            if (line.startsWith(gate + " ")) {
                return true;
            }
        }
        return false;
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

    private QuantumProgram initializeQuantumProgram() {
        QuantumProgram quantumProgram = new QuantumProgram();
        quantumProgram.setQubits(new ArrayList<>());
        quantumProgram.setClassicBits(new ArrayList<>());
        quantumProgram.setGates(new ArrayList<>());
        quantumProgram.setMeasures(new ArrayList<>());
        quantumProgram.setASTNodes(new ArrayList<>());
        return quantumProgram;
    }
}
