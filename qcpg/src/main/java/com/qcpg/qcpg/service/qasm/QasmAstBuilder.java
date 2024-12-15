package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.antlr4.qasm3Parser;
import com.qcpg.qcpg.antlr4.qasm3ParserBaseVisitor;
import com.qcpg.qcpg.graph.AstGraph;
import com.qcpg.qcpg.graph.AstNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class QasmAstBuilder extends qasm3ParserBaseVisitor<AstNode> {

    private AstGraph graph;
    private static final AtomicLong ID_GEN = new AtomicLong(1);
    private String currentFile;
    private List<String> codeLines;

    public QasmAstBuilder() {

    }

    public AstGraph build(qasm3Parser.ProgramContext programCtx, String fileName, List<String> codeLines) {
        this.currentFile = fileName;
        this.codeLines = codeLines;
        this.graph = new AstGraph();
        AstNode root = visitProgram(programCtx);
        graph.setRoot(root);
        return graph;
    }

    private AstNode createNode(String baseName, Map<String, Object> additionalProps, ParserRuleContext ctx) {
        AstNode node = AstNode.builder().id(ID_GEN.getAndIncrement()).build();
        assignNodeData(node, baseName, additionalProps, ctx);
        return node;
    }

    private void assignNodeData(AstNode node, String baseName, Map<String, Object> additionalProps, ParserRuleContext ctx) {
        node.getLabels().add(baseName.toUpperCase());
        node.getLabels().add(baseName.toUpperCase() + "_DETAIL");

        Map<String, Object> props = new HashMap<>();
        props.put("author", "system");
        props.put("version", "1.0");
        props.put("node_type", baseName);
        props.put("file", this.currentFile);

        int lineNumber = ctx != null ? ctx.getStart().getLine() : 0;
        String codeLine = (lineNumber > 0 && lineNumber <= codeLines.size()) ? codeLines.get(lineNumber - 1) : "";
        codeLine = codeLine.replace("\"", "'");
        props.put("code_line", codeLine);
        props.put("line_of_code", lineNumber);
        props.put("code_line", codeLine);

        if (additionalProps != null) {
            props.putAll(additionalProps);
        }
        node.setProperties(props);
    }

    private String extractWithRegex(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : null;
    }

    @Override
    public AstNode visitProgram(qasm3Parser.ProgramContext ctx) {
        AstNode node = createNode("PROGRAM", Map.of("info", "Root of QASM program"), ctx);

        if (ctx.version() != null) {
            AstNode versionNode = visit(ctx.version());
            if (versionNode != null) {
                node.getChildren().add(versionNode);
            }
        }

        ctx.statementOrScope().forEach(soc -> {
            AstNode childNode = soc.accept(this);
            if (childNode != null) {
                node.getChildren().add(childNode);
            }
        });

        return node;
    }

    @Override
    public AstNode visitVersion(qasm3Parser.VersionContext ctx) {
        return createNode("VERSION", Map.of("version_text", ctx.getText()), ctx);
    }

    @Override
    public AstNode visitIncludeStatement(qasm3Parser.IncludeStatementContext ctx) {
        String includedFile = ctx.StringLiteral().getText().replace("\"", "");
        return createNode("INCLUDE", Map.of("included_file", includedFile), ctx);
    }

    @Override
    public AstNode visitGateStatement(qasm3Parser.GateStatementContext ctx) {
        Map<String, Object> props = new HashMap<>();
        String fullText = ctx.getText();
        props.put("raw_text", fullText);

        String gateName = ctx.Identifier().getText();
        props.put("gate_name", gateName);

        // Extraer parámetros y qubits si existen
        String parameters = extractWithRegex(fullText, "\\((.*?)\\)");
        props.put("parameters", parameters != null ? parameters : "none");

        String qubits = extractWithRegex(fullText, "\\[(.*?)\\]");
        props.put("qubits", qubits != null ? qubits : "none");

        return createNode("GATE_STATEMENT", props, ctx);
    }

    @Override
    public AstNode visitQuantumDeclarationStatement(qasm3Parser.QuantumDeclarationStatementContext ctx) {
        Map<String, Object> props = new HashMap<>();
        String fullText = ctx.getText();
        props.put("raw_text", fullText);
    
        String quantumType = ctx.getChild(0).getText();
        props.put("quantum_type", quantumType);
    
        String identifier = ctx.getChildCount() > 1 ? ctx.getChild(1).getText() : "unknown";
        props.put("identifier", identifier);
    
        String size = ctx.getChildCount() > 2 ? ctx.getChild(2).getText() : "1";
        props.put("size", size);
    
        return createNode("QUANTUM_DECLARATION_STATEMENT", props, ctx);
    }
    
    

    @Override
    public AstNode visitChildren(RuleNode node) {
        int ruleIndex = node.getRuleContext().getRuleIndex();
        String nodeName = (ruleIndex >= 0 && ruleIndex < qasm3Parser.ruleNames.length)
                ? qasm3Parser.ruleNames[ruleIndex]
                : "UNKNOWN_RULE";

        ParserRuleContext ctx = (node.getRuleContext() instanceof ParserRuleContext)
                ? (ParserRuleContext)node.getRuleContext()
                : null;

        AstNode current = createNode(nodeName.toUpperCase(), Map.of("info", "Derived from rule " + nodeName), ctx);

        for (int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            AstNode childNode = child.accept(this);
            if (childNode != null) {
                current.getChildren().add(childNode);
            }
        }
        return current;
    }

    @Override
    public AstNode visitTerminal(TerminalNode node) {
        Map<String,Object> props = new HashMap<>();
        props.put("text", node.getText());
        props.put("token_type", node.getSymbol().getType());
        int lineNumber = node.getSymbol().getLine();
        props.put("line_of_code", lineNumber);
        String codeLine = (lineNumber > 0 && lineNumber <= codeLines.size()) ? codeLines.get(lineNumber-1) : "";
        props.put("code_line", codeLine);

        return createNode("TERMINAL", props, null);
    }
}
