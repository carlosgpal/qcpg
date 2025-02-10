package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.antlr4.qasm3Parser;
import com.qcpg.qcpg.antlr4.qasm3ParserBaseVisitor;
import com.qcpg.qcpg.model.graphCreation.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for constructing an Abstract Syntax Tree (AST) from QASM code using
 * ANTLR.
 * This builder creates a structured AST graph with nodes and edges, enriching
 * the data
 * with metadata such as line numbers, node types, and code patterns.
 */
@Service
public class QasmAstBuilder extends qasm3ParserBaseVisitor<AstNode> {

    private AstGraph graph;
    private static final AtomicLong ID_GEN = new AtomicLong(1); // Global ID generator for nodes
    private String currentFile; // File being processed
    private List<String> codeLines; // Lines of QASM code from the current file

    /**
     * Builds the AST graph for a given QASM program context.
     *
     * @param programCtx The program context parsed by ANTLR.
     * @param fileName   The name of the file being processed.
     * @param codeLines  The list of lines from the QASM file.
     * @return The constructed `AstGraph` containing the program's AST.
     */
    public AstGraph build(qasm3Parser.ProgramContext programCtx, String fileName, List<String> codeLines) {
        this.currentFile = fileName;
        this.codeLines = codeLines;
        this.graph = new AstGraph();
        AstNode root = visitProgram(programCtx);
        graph.setRoot(root);
        return graph;
    }

    /**
     * Creates an `AstNode` with the provided base name, properties, and context.
     *
     * @param baseName        The base name/type of the node.
     * @param additionalProps Additional properties to include in the node.
     * @param ctx             The parser context for metadata extraction.
     * @return The created `AstNode`.
     */
    private AstNode createNode(String baseName, Map<String, Object> additionalProps, ParserRuleContext ctx) {
        AstNode node = AstNode.builder().id(ID_GEN.getAndIncrement()).build();
        assignNodeData(node, baseName, additionalProps, ctx);
        return node;
    }

    /**
     * Assigns metadata and labels to a node based on its base name and context.
     *
     * @param node            The `AstNode` to be updated.
     * @param baseName        The base name/type of the node.
     * @param additionalProps Additional properties to include in the node.
     * @param ctx             The parser context for metadata extraction.
     */
    private void assignNodeData(AstNode node, String baseName, Map<String, Object> additionalProps,
            ParserRuleContext ctx) {
        node.getLabels().add("AST_NODE");
        node.getLabels().add("QASM_AST");

        Map<String, Object> props = new HashMap<>();
        props.put("author", "system");
        props.put("version", "1.0");
        props.put("node_type", baseName);
        props.put("file", this.currentFile);

        // Extract line information from context
        int lineNumber = (ctx != null) ? ctx.getStart().getLine() : 0;
        String originalLine = (lineNumber > 0 && lineNumber <= codeLines.size())
                ? codeLines.get(lineNumber - 1)
                : "";
        String codeLine = originalLine.replace("\"", "'");
        props.put("line_of_code", lineNumber);
        props.put("code_line", codeLine);
        props.put("raw_line_of_code", originalLine);
        props.put("normalized_code_line", codeLine.trim());

        if (additionalProps != null) {
            props.putAll(additionalProps);
        }

        // Detect patterns for statement-level nodes
        if (baseName.toLowerCase().contains("statement")
                || baseName.equalsIgnoreCase("program")
                || baseName.equalsIgnoreCase("scope")) {
            props = detectPatterns(baseName, props, node);
        }

        // Adjust labels based on properties
        if (props.containsKey("descriptive_type")) {
            String descType = (String) props.get("descriptive_type");
            node.getLabels().add(descType.toUpperCase());
        }

        String nodeType = (String) props.getOrDefault("node_type", "");
        if (nodeType.equals("program")) {
            node.getLabels().add("PROGRAM_ROOT");
        }
        if (nodeType.toLowerCase().contains("statement")) {
            node.getLabels().add("STATEMENT");
        }
        if (nodeType.toLowerCase().contains("expression")) {
            node.getLabels().add("EXPRESSION");
        }
        if (nodeType.toLowerCase().contains("declaration")) {
            node.getLabels().add("DECLARATION");
        }

        props.replaceAll((key, value) -> {
            if (value instanceof String) {
                return ((String) value).replace("\"", "'").trim();
            }
            return value;
        });

        node.setProperties(props);
    }

    /**
     * Detects specific patterns in the code to enrich the node's properties.
     *
     * @param baseName The base name/type of the node.
     * @param props    The existing properties of the node.
     * @param node     The current `AstNode` being analyzed.
     * @return The enriched properties map.
     */
    private Map<String, Object> detectPatterns(String baseName, Map<String, Object> props, AstNode node) {
        String code = (String) props.getOrDefault("code_line", "");
        String nodeType = (String) props.getOrDefault("node_type", "");

        // Match OPENQASM version declaration
        if (code.matches(".*OPENQASM\\s+3\\.0.*")) {
            props.put("descriptive_type", "VERSION_DECLARATION");
            props.put("display_name", "OPENQASM 3.0 version declaration");
        }

        // Detect include statements
        if (nodeType.toLowerCase().contains("includestatement")) {
            Pattern includePattern = Pattern.compile("\\binclude\\s+(\"[^\"]*\"|'[^']*')\\s*;");
            Matcher mInclude = includePattern.matcher(code);
            if (mInclude.find()) {
                String includedFile = mInclude.group(1);
                props.put("descriptive_type", "INCLUDE_STATEMENT");
                props.put("included_file", includedFile);
                props.put("display_name", "Include file: " + mInclude.group(1));
            }
        }

        // Detect quantum bit declaration statements
        if (nodeType.toLowerCase().contains("quantumdeclarationstatement")) {
            Pattern qubitDeclWithSize = Pattern.compile("\\bqubit\\[(\\d+)\\]\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*;");
            Matcher mQubitWithSize = qubitDeclWithSize.matcher(code);
            Pattern qubitDeclNoSize = Pattern.compile("\\bqubit\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*;");
            Matcher mQubitNoSize = qubitDeclNoSize.matcher(code);

            if (mQubitWithSize.find()) {
                props.put("descriptive_type", "QUBIT_DECLARATION");
                props.put("qubit_size", mQubitWithSize.group(1));
                props.put("qubit_name", mQubitWithSize.group(2));
                props.put("display_name",
                        "Qubit declaration: " + mQubitWithSize.group(2) + "[" + mQubitWithSize.group(1) + "]");
            } else if (mQubitNoSize.find()) {
                props.put("descriptive_type", "QUBIT_DECLARATION");
                props.put("qubit_size", "1");
                props.put("qubit_name", mQubitNoSize.group(1));
                props.put("display_name", "Qubit declaration: " + mQubitNoSize.group(1) + "[1]");
            }
        }

        // Detect classical bit declaration statements
        if (nodeType.toLowerCase().contains("classicaldeclarationstatement")) {
            Pattern bitDecl = Pattern.compile("\\bbit\\[(\\d+)\\]\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*;");
            Matcher mBit = bitDecl.matcher(code);

            if (mBit.find()) {
                props.put("descriptive_type", "BIT_DECLARATION");
                props.put("bit_size", mBit.group(1));
                props.put("bit_name", mBit.group(2));
                props.put("display_name", "Bit declaration: " + mBit.group(2) + "[" + mBit.group(1) + "]");
            }
        }

        // Detect old qasm2.0 declarations
        if (nodeType.toLowerCase().contains("oldstyledeclarationstatement")) {
            Pattern oldCreg = Pattern.compile("\\bcreg\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\[(\\d+)\\]\\s*;");
            Matcher mOldCreg = oldCreg.matcher(code);
            Pattern oldQreg = Pattern.compile("\\bqreg\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\[(\\d+)\\]\\s*;");
            Matcher mOldQreg = oldQreg.matcher(code);
            if (mOldQreg.find()) {
                props.put("descriptive_type", "QUBIT_DECLARATION");
                props.put("qubit_size", mOldQreg.group(2));
                props.put("qubit_name", mOldQreg.group(1));
                props.put("display_name",
                        "Qubit declaration: " + mOldQreg.group(1) + "[" + mOldQreg.group(2) + "]");
            } else if (mOldCreg.find()) {
                props.put("descriptive_type", "BIT_DECLARATION");
                props.put("bit_size", mOldCreg.group(2));
                props.put("bit_name", mOldCreg.group(1));
                props.put("display_name",
                        "Bit declaration: " + mOldCreg.group(1) + "[" + mOldCreg.group(2) + "]");
            }
        }

        // Detect gate call statements
        if (nodeType.toLowerCase().contains("gatecallstatement")) {
            Pattern gateCallIndexed = Pattern
                    .compile("\\b([a-zA-Z_][0-9a-zA-Z_]*)\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\[(\\d+)\\]\\s*;");
            Matcher mGateIndexed = gateCallIndexed.matcher(code);

            if (mGateIndexed.find()) {
                props.put("descriptive_type", "GATE_CALL");
                props.put("gate_name", mGateIndexed.group(1));
                props.put("target_qubit", mGateIndexed.group(2));
                props.put("target_index", mGateIndexed.group(3));
                props.put("display_name", "Gate call: " + mGateIndexed.group(1)
                        + " on " + mGateIndexed.group(2)
                        + "[" + mGateIndexed.group(3) + "]");
            }
        }

        if (nodeType.toLowerCase().contains("gatecallstatement")) {
            if (code.trim().startsWith("if")) {
                Pattern inlineIfPattern = Pattern.compile("^if\\s*\\(([^)]*)\\)\\s*(.+);$");
                Matcher mInlineIf = inlineIfPattern.matcher(code.trim());
                if (mInlineIf.find()) {
                    String condition = mInlineIf.group(1).trim();
                    String gateCallStr = mInlineIf.group(2).trim();
                    Pattern innerGateCall = Pattern.compile("^([a-zA-Z_][0-9a-zA-Z_]*)(\\s*(\\([^)]*\\))?)\\s+(.+)$");
                    Matcher mInner = innerGateCall.matcher(gateCallStr);
                    if (mInner.find()) {
                        String gateName = mInner.group(1);
                        // String gateParams = mInner.group(2) != null ? mInner.group(2).trim() : "";
                        String operands = mInner.group(4).trim();
                        props.put("descriptive_type", "GATE_CALL");
                        props.put("gate_name", gateName);
                        props.put("parameters", "(" + condition + ")");
                        props.put("operands", operands);
                        props.put("display_name", "Gate call: " + gateName + " (" + condition + ") on " + operands);
                    } else {
                        System.err.println("Warning: Unable to match inner gate call pattern in code: " + gateCallStr);
                    }
                } else {
                    System.err.println("Warning: Unable to match inline if pattern in code: " + code);
                }
            } else {
                Pattern gateCall = Pattern.compile(
                        "\\b([a-zA-Z_][0-9a-zA-Z_]*)\\s*(\\([^)]*\\))?\\s+([^;]+);");
                Matcher mGateCall = gateCall.matcher(code);

                if (mGateCall.find()) {
                    String gateName = mGateCall.group(1);
                    String parameters = Optional.ofNullable(mGateCall.group(2)).orElse("").trim();
                    String operands = mGateCall.group(3).trim();

                    if (operands.isEmpty()) {
                        throw new IllegalArgumentException("Gate call detected with empty operands: " + code);
                    }

                    props.put("descriptive_type", "GATE_CALL");
                    props.put("gate_name", gateName);
                    props.put("parameters", parameters);
                    props.put("operands", operands);
                    props.put("display_name", "Gate call: " + gateName + " " + parameters + " on " + operands);
                } else {
                    // Loguear si no se detecta un patrón válido
                    System.err.println("Warning: Unable to match gate call pattern in code: " + code);
                }
            }
        }

        // Detect complex gates statements
        if (nodeType.toLowerCase().contains("gatestatement")) {
            String initialLine = (String) props.get("raw_line_of_code");

            if (initialLine != null && !initialLine.isEmpty()) {
                Pattern gateDefStart = Pattern.compile(
                        "^\\s*gate\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*\\(([^)]*)\\)\\s+[a-zA-Z_][0-9a-zA-Z_]*\\s*\\{");
                Pattern gateDeclarationPattern = Pattern
                        .compile("^\\s*gate\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*\\{");
                Matcher mGateDefStart = gateDefStart.matcher(initialLine);
                Matcher mGateDeclaration = gateDeclarationPattern.matcher(initialLine);

                if (mGateDefStart.find()) {
                    props.put("descriptive_type", "GATE_DEFINITION");
                    props.put("gate_def_name", mGateDefStart.group(1));
                    props.put("gate_def_params", mGateDefStart.group(2).trim());
                    props.put("display_name", "Gate definition start: " + mGateDefStart.group(1) + "("
                            + mGateDefStart.group(2).trim() + ")");
                }
                if (mGateDeclaration.find()) {
                    props.put("descriptive_type", "GATE_DECLARATION");
                    props.put("gate_name", mGateDeclaration.group(1));
                    props.put("gate_params", mGateDeclaration.group(2).trim());
                    props.put("display_name",
                            "Gate declaration: " + mGateDeclaration.group(1) + " " + mGateDeclaration.group(2).trim());
                }
            }
        }

        // Detect complex def statements
        if (nodeType.toLowerCase().contains("defstatement")) {
            String initialLine = (String) props.get("raw_line_of_code");

            if (initialLine != null && !initialLine.isEmpty()) {
                Pattern defPattern = Pattern.compile("^\\s*def\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*\\(([^)]*)\\)\\s*\\{");
                Matcher mDef = defPattern.matcher(initialLine);
                if (mDef.find()) {
                    props.put("descriptive_type", "COMPLEX_DEFINITION");
                    props.put("def_name", mDef.group(1));
                    props.put("def_params", mDef.group(2).trim());
                    props.put("display_name", "Definition start: " + mDef.group(1) + "(" + mDef.group(2).trim() + ")");
                    System.out.println("Regex Match for Definition Found: true");
                }
            }
        }

        // Detect measures statements
        if (nodeType.toLowerCase().contains("measurearrowassignmentstatement")) {
            Pattern measure = Pattern.compile(
                    "\\bmeasure\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\[(\\d+)\\]\\s*->\\s*([a-zA-Z_][0-9a-zA-Z_]*)\\[(\\d+)\\]\\s*;");
            Matcher mMeasure = measure.matcher(code);
            Pattern measureNoIndex = Pattern
                    .compile("\\bmeasure\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*->\\s*([a-zA-Z_][0-9a-zA-Z_]*)\\s*;");
            Matcher mMeasureNoIndex = measureNoIndex.matcher(code);
            if (mMeasure.find()) {
                props.put("descriptive_type", "MEASURE_CALL");
                props.put("measured_qubit", mMeasure.group(1));
                props.put("measured_qubit_index", mMeasure.group(2));
                props.put("destination_bit", mMeasure.group(3));
                props.put("destination_bit_index", mMeasure.group(4));
                props.put("display_name", "Measure: " + mMeasure.group(1) + "[" + mMeasure.group(2) + "] -> "
                        + mMeasure.group(3) + "[" + mMeasure.group(4) + "]");
            } else if (mMeasureNoIndex.find()) {
                props.put("descriptive_type", "MEASURE_CALL");
                props.put("measured_qubit", mMeasureNoIndex.group(1));
                props.put("measured_qubit_index", "");
                props.put("destination_bit", mMeasureNoIndex.group(2));
                props.put("destination_bit_index", "");
                props.put("display_name",
                        "Measure: " + mMeasureNoIndex.group(1) + " -> " + mMeasureNoIndex.group(2));
            }
        }

        // Detect reset statements
        if (nodeType.toLowerCase().contains("resetstatement")) {
            Pattern resetPat = Pattern.compile("\\breset\\s+([^;]+);");
            Matcher mReset = resetPat.matcher(code);
            if (mReset.find()) {
                String targets = mReset.group(1).trim();
                props.put("descriptive_type", "RESET_STATEMENT");
                props.put("reset_qubit", targets);
                props.put("expand_register", targets.equals("q"));
                props.put("display_name", "Reset: " + targets);
            }
        }

        // Detect barrier statementss
        if (nodeType.toLowerCase().contains("barrierstatement")) {
            Pattern barrierPat = Pattern.compile("\\bbarrier\\s+([^;]+);");
            Matcher mBarrier = barrierPat.matcher(code);
            if (mBarrier.find()) {
                String targets = mBarrier.group(1).trim();
                props.put("descriptive_type", "BARRIER_STATEMENT");
                props.put("barrier_operands", targets);
                props.put("expand_register", targets.equals("q"));
                props.put("display_name", "Barrier: " + targets);
            }
        }

        // Detect if statements
        if (nodeType.toLowerCase().contains("ifstatement")) {
            Pattern ifPattern = Pattern.compile("\\bif\\s*\\(([^)]*)\\)");
            Matcher mIf = ifPattern.matcher(code);
            if (mIf.find()) {
                props.put("descriptive_type", "IF_STATEMENT");
                props.put("condition_expression", mIf.group(1).trim());
                props.put("display_name", "If condition: " + mIf.group(1).trim());
            }
        }

        // Detect for statements
        if (nodeType.toLowerCase().contains("forstatement")) {
            Pattern forPattern = Pattern
                    .compile("\\bfor\\s+(int\\s+)?([a-zA-Z_][0-9a-zA-Z_]*)\\s+in\\s+\\[(\\d+):(\\d+)\\]");
            Matcher mFor = forPattern.matcher(code);
            if (mFor.find()) {
                props.put("descriptive_type", "FOR_LOOP");
                props.put("loop_var", mFor.group(2));
                props.put("loop_start", mFor.group(3));
                props.put("loop_end", mFor.group(4));
                props.put("display_name",
                        "For loop " + mFor.group(2) + " in [" + mFor.group(3) + ":" + mFor.group(4) + "]");
            }
        }

        // Detect constants statements
        if (nodeType.toLowerCase().contains("constdeclarationstatement")) {
            Pattern constDecl = Pattern
                    .compile("\\bconst\\s+([a-zA-Z0-9\\[\\]]+)\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*=\\s*([^;]+);");
            Matcher mConst = constDecl.matcher(code);
            if (mConst.find()) {
                props.put("descriptive_type", "CONST_DECLARATION");
                props.put("const_type", mConst.group(1));
                props.put("const_name", mConst.group(2));
                props.put("const_value", mConst.group(3));
                props.put("display_name", "Const declaration: " + mConst.group(2)
                        + " of type " + mConst.group(1)
                        + " = " + mConst.group(3));
            }
        }

        // Detect assignment that involves a measure (e.g., c[0] = measure q[0];)
        // Detect index assigment statements
        if (nodeType.toLowerCase().contains("assignmentstatement")) {
            Pattern measureAssignment = Pattern.compile(
                    "([a-zA-Z_][0-9a-zA-Z_]*)\\[(\\d+)\\]\\s*=\\s*measure\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\[(\\d+)\\]\\s*;");
            Matcher mMeasureAssignment = measureAssignment.matcher(code);
            Pattern assignment = Pattern.compile("([a-zA-Z_][0-9a-zA-Z_]*)\\[(\\d+)\\]\\s*=\\s*([^;]+);");
            Matcher mAssign = assignment.matcher(code);
            if (mMeasureAssignment.find()) {
                props.put("descriptive_type", "MEASURE_CALL");
                props.put("measured_qubit", mMeasureAssignment.group(3));
                props.put("measured_qubit_index", mMeasureAssignment.group(4));
                props.put("destination_bit", mMeasureAssignment.group(1));
                props.put("destination_bit_index", mMeasureAssignment.group(2));
                props.put("display_name",
                        "Measure: " + mMeasureAssignment.group(3) + "[" + mMeasureAssignment.group(4) + "] -> "
                                + mMeasureAssignment.group(1) + "[" + mMeasureAssignment.group(2) + "]");
            } else if (mAssign.find()) {
                props.put("descriptive_type", "INDEX_ASSIGNMENT");
                props.put("assigned_array", mAssign.group(1));
                props.put("assigned_index", mAssign.group(2));
                props.put("assigned_value", mAssign.group(3));
                props.put("display_name", "Assignment: " + mAssign.group(1)
                        + "[" + mAssign.group(2)
                        + "] = " + mAssign.group(3));
            }
        }

        // Detect classical var statements
        Pattern declSimple = Pattern
                .compile("\\b(int|float\\[\\d+\\]|bool)\\s+([a-zA-Z_][0-9a-zA-Z_]*)(\\s*=\\s*([^;]+))?;");
        Matcher mDeclSimple = declSimple.matcher(code);
        if (mDeclSimple.find() && !props.containsKey("descriptive_type")) {
            props.put("descriptive_type", "CLASSICAL_DECLARATION");
            props.put("var_type", mDeclSimple.group(1));
            props.put("var_name", mDeclSimple.group(2));
            if (mDeclSimple.group(4) != null) {
                props.put("var_value", mDeclSimple.group(4));
                props.put("display_name", "Declaration: " + mDeclSimple.group(2)
                        + " of type " + mDeclSimple.group(1)
                        + " = " + mDeclSimple.group(4));
            } else {
                props.put("display_name", "Declaration: " + mDeclSimple.group(2)
                        + " of type " + mDeclSimple.group(1));
            }
        }

        // Detect array statements
        Pattern arrayDecl = Pattern.compile(
                "\\barray\\[(\\d+(?:,\\s*\\d+)*)\\]\\s+([a-zA-Z0-9\\[\\]]+)\\s+([a-zA-Z_][0-9a-zA-Z_]*)\\s*=\\s*\\{([^}]*)\\};");
        Matcher mArrayDecl = arrayDecl.matcher(code);
        if (mArrayDecl.find()) {
            props.put("descriptive_type", "ARRAY_DECLARATION");
            props.put("array_dimensions", mArrayDecl.group(1));
            props.put("array_type", mArrayDecl.group(2));
            props.put("array_name", mArrayDecl.group(3));
            props.put("array_values", mArrayDecl.group(4).trim());
            props.put("display_name", "Array declaration: "
                    + mArrayDecl.group(3)
                    + " of type " + mArrayDecl.group(2)
                    + " dims[" + mArrayDecl.group(1) + "]");
        }

        // Default statements
        if (!props.containsKey("display_name")) {
            String defaultDisplay = nodeType;
            if (code.trim().length() > 0) {
                defaultDisplay += " - " + code.trim();
            }
            props.put("display_name", defaultDisplay);
        }

        // Check if the line of code contains a comparison operator (==)
        if (code.matches(".*==.*")) {
            // If a comparison operator is detected, add a property indicating its presence
            props.put("has_comparison", "true");
        }

        // Check if the line of code contains any arithmetic operator (+, -, *, /)
        if (code.matches(".*\\+.*") || code.matches(".*-.*")
                || code.matches(".*\\*.*") || code.matches(".*/.*")) {
            // If any arithmetic operator is detected, add a property indicating its
            // presence
            props.put("has_arithmetic", "true");
        }

        return props;
    }

    /**
     * Visits a QASM program context and constructs the root node of the AST.
     *
     * @param ctx The program context parsed by ANTLR.
     * @return The root `AstNode` of the program.
     */
    @Override
    public AstNode visitProgram(qasm3Parser.ProgramContext ctx) {
        AstNode node = createNode("program",
                Map.of("info", "Root of QASM program", "antlr_rule", "program"),
                ctx);
        for (var child : ctx.statementOrScope()) {
            AstNode c = child.accept(this);
            if (c != null)
                node.getChildren().add(c);
        }
        return node;
    }

    /**
     * Visits child nodes and constructs corresponding AST nodes.
     *
     * @param node The parent rule node.
     * @return The constructed `AstNode`.
     */
    @Override
    public AstNode visitChildren(RuleNode node) {
        int ruleIndex = node.getRuleContext().getRuleIndex();
        String nodeName = (ruleIndex >= 0 && ruleIndex < qasm3Parser.ruleNames.length)
                ? qasm3Parser.ruleNames[ruleIndex]
                : "UNKNOWN_RULE";

        ParserRuleContext ctx = (node.getRuleContext() instanceof ParserRuleContext)
                ? (ParserRuleContext) node.getRuleContext()
                : null;

        AstNode current = createNode(nodeName,
                Map.of("info", "Derived from rule " + nodeName, "antlr_rule", nodeName),
                ctx);

        current.getLabels().add("RULE");

        for (int i = 0; i < node.getChildCount(); i++) {
            var child = node.getChild(i);
            AstNode childNode = child.accept(this);
            if (childNode != null && !current.getChildren().contains(childNode)) {
                current.getChildren().add(childNode);
            }
        }
        return current;
    }

    /**
     * Visits a terminal node and constructs a corresponding AST terminal node.
     *
     * @param node The terminal node.
     * @return The constructed `AstNode`.
     */
    @Override
    public AstNode visitTerminal(TerminalNode node) {
        Map<String, Object> props = new HashMap<>();
        props.put("text", node.getText());
        props.put("token_type", node.getSymbol().getType());
        props.put("file", this.currentFile);

        int lineNumber = node.getSymbol().getLine();
        props.put("line_of_code", lineNumber);

        String originalLine = (lineNumber > 0 && lineNumber <= codeLines.size())
                ? codeLines.get(lineNumber - 1)
                : "";
        String codeLine = originalLine.replace("\"", "'");
        props.put("code_line", codeLine);
        props.put("raw_line_of_code", originalLine);
        props.put("normalized_code_line", codeLine.trim());

        AstNode terminalNode = AstNode.builder()
                .id(ID_GEN.getAndIncrement())
                .labels(new ArrayList<>(List.of("AST_NODE", "QASM_AST", "TERMINAL")))
                .properties(props)
                .build();

        props.replaceAll((key, value) -> {
            if (value instanceof String) {
                return ((String) value).replace("\"", "'").trim();
            }
            return value;
        });

        return terminalNode;
    }
}