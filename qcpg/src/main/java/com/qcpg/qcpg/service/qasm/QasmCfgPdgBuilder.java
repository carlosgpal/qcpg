package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.model.graphCreation.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for building Control Flow Graph (CFG) and Program Dependency Graph
 * (PDG) from the AST.
 * This builder connects nodes in the AST based on their control flow and
 * dependency relationships.
 */
@Service
public class QasmCfgPdgBuilder {

    private String currentFile; // The name of the file being processed.

    // Allowed labels to filter AST nodes for CFG and PDG construction.
    private static final Set<String> ALLOWED_LABELS = new HashSet<>(Arrays.asList(
            "ARRAY_DECLARATION",
            "GATE_DEFINITION",
            "INDEX_ASSIGNMENT",
            "CONST_DECLARATION",
            "FOR_LOOP",
            "IF_STATEMENT",
            "BARRIER_STATEMENT",
            "RESET_STATEMENT",
            "MEASURE_CALL",
            "GATE_CALL",
            "BIT_DECLARATION",
            "QUBIT_DECLARATION",
            "INCLUDE_STATEMENT",
            "VERSION_DECLARATION"));

    /**
     * Builds the Control Flow Graph (CFG) edges from the given AST root node.
     *
     * @param astRoot  The root of the AST.
     * @param fileName The name of the file being processed.
     * @return A list of edges representing the CFG.
     */
    public List<EdgeBase> buildCfgEdges(AstNode astRoot, String fileName) {
        this.currentFile = fileName;
        List<EdgeBase> edges = new ArrayList<>();
        if (astRoot == null)
            return edges;

        // Collect all nodes in the AST.
        List<AstNode> allNodes = new ArrayList<>();
        collectNodes(astRoot, allNodes);

        // Filter nodes to include only those with allowed labels.
        List<AstNode> statements = new ArrayList<>();
        for (AstNode n : allNodes) {
            if (hasAllowedLabel(n)) {
                statements.add(n);
            }
        }

        // Sort statements by their line number.
        statements.sort(Comparator.comparingLong(this::lineNumber));

        // Create entry and exit nodes for the CFG.
        AstNode entry = createAuxNode("CFG_ENTRY", "entry_node");
        AstNode exit = createAuxNode("CFG_EXIT", "exit_node");
        List<EdgeBase> cfgEdges = new ArrayList<>();

        // Connect entry node to the first statement or to the exit node if there are no
        // statements.
        if (!statements.isEmpty()) {
            cfgEdges.add(edge(entry, statements.get(0), "CFG", "flow"));
        } else {
            cfgEdges.add(edge(entry, exit, "CFG", "flow"));
            edges.addAll(cfgEdges);
            return edges;
        }

        // Create CFG edges between statements.
        for (int i = 0; i < statements.size() - 1; i++) {
            AstNode current = statements.get(i);
            AstNode next = statements.get(i + 1);

            if (isIfStatement(current)) {
                // Handle if-condition control flow.
                AstNode ifCond = createConditionNode("if_condition", "IF_CONDITION");
                ifCond.getProperties().put("descriptive_type", "IF_CONDITION");
                ifCond.getProperties().put("line_of_code", lineNumber(current));

                cfgEdges.add(edge(current, ifCond, "CFG_CONTROL", "control_flow"));
                cfgEdges.add(edge(ifCond, next, "CFG_TRUE", "if_true_branch"));

                AstNode falseTarget = (i + 2 < statements.size()) ? statements.get(i + 2) : exit;
                cfgEdges.add(edge(ifCond, falseTarget, "CFG_FALSE", "if_false_branch"));
                i++;
            } else if (isLoopStatement(current)) {
                // Handle loop control flow.
                AstNode loopCond = createConditionNode("loop_condition", "LOOP_CONDITION");
                loopCond.getProperties().put("descriptive_type", "LOOP_CONDITION");
                loopCond.getProperties().put("line_of_code", lineNumber(current));

                cfgEdges.add(edge(current, loopCond, "CFG_CONTROL", "control_flow"));
                cfgEdges.add(edge(loopCond, next, "CFG_TRUE", "loop_true_branch"));

                AstNode falseTarget = (i + 2 < statements.size()) ? statements.get(i + 2) : exit;
                cfgEdges.add(edge(loopCond, falseTarget, "CFG_FALSE", "loop_false_branch"));
                i++;
            } else {
                // Linear flow between statements.
                cfgEdges.add(edge(current, next, "CFG", "linear_flow"));
            }
        }

        // Connect the last statement to the exit node.
        if (!statements.isEmpty()) {
            AstNode lastStmt = statements.get(statements.size() - 1);
            boolean hasExit = cfgEdges.stream().anyMatch(
                    e -> e.getFrom() == lastStmt && ((String) e.getProperties().get("rel_type")).startsWith("CFG"));
            if (!hasExit) {
                cfgEdges.add(edge(lastStmt, exit, "CFG", "end_flow"));
            }
        }

        edges.addAll(cfgEdges);
        return edges;
    }

    /**
     * Builds the Program Dependency Graph (PDG) edges from the AST nodes.
     *
     * @param allNodes A list of AST nodes.
     * @return A list of edges representing the PDG.
     */
    public List<EdgeBase> buildPdgEdges(List<AstNode> allNodes) {
        List<EdgeBase> edges = new ArrayList<>();
        if (allNodes == null || allNodes.isEmpty())
            return edges;

        // Filter nodes to include only those with allowed labels.
        List<AstNode> filtered = new ArrayList<>();
        for (AstNode n : allNodes) {
            if (hasAllowedLabel(n)) {
                filtered.add(n);
            }
        }

        // Map to track the last definition of variables.
        Map<String, AstNode> lastDef = new HashMap<>();
        for (AstNode n : filtered) {
            defineVar(n, lastDef);
        }

        // Create data dependency edges for variables used in statements.
        for (AstNode n : filtered) {
            String descriptiveType = (String) n.getProperties().get("descriptive_type");
        
            if ("CONST_DECLARATION".equals(descriptiveType)) {
                String constName = (String) n.getProperties().get("const_name");
                if (constName != null && lastDef.containsKey(constName)) {
                    for (AstNode refNode : filtered) {
                        String parameters = (String) refNode.getProperties().getOrDefault("parameters", "");
                        if (parameters.contains(constName)) {
                            edges.add(edge(lastDef.get(constName), refNode, "PDG_DATA", "data_dep_const"));
                        }
                    }
                }
            } else if ("GATE_CALL".equals(descriptiveType)) {
                String operands = (String) n.getProperties().get("operands");
                if (operands != null) {
                    for (String operand : operands.split(",")) {
                        String normalizedOperand = operand.trim();
                        if (lastDef.containsKey(normalizedOperand)) {
                            edges.add(edge(lastDef.get(normalizedOperand), n, "PDG_DATA", "data_dep"));
                        }
                    }
                }
            } else if ("MEASURE_CALL".equals(descriptiveType)) {
                String measuredQubit = (String) n.getProperties().get("measured_qubit");
                String measuredQubitIndex = (String) n.getProperties().get("measured_qubit_index");
                String destinationBit = (String) n.getProperties().get("destination_bit");
                String destinationBitIndex = (String) n.getProperties().get("destination_bit_index");

                String measuredQubitKey = measuredQubit + "[" + measuredQubitIndex + "]";
                String destinationBitKey = destinationBit + "[" + destinationBitIndex + "]";

                if (measuredQubit != null && lastDef.containsKey(measuredQubitKey)) {
                    edges.add(edge(lastDef.get(measuredQubitKey), n, "PDG_DATA", "data_dep_qubit"));
                } else {
                    System.err.println("Warning: Measured qubit not found in lastDef: " + measuredQubitKey);
                }

                if (destinationBit != null && lastDef.containsKey(destinationBitKey)) {
                    edges.add(edge(lastDef.get(destinationBitKey), n, "PDG_DATA", "data_dep_bit"));
                } else {
                    System.err.println("Warning: Destination bit not found in lastDef: " + destinationBitKey);
                }
            } else if ("RESET_STATEMENT".equals(descriptiveType)) {
                String resetQubit = (String) n.getProperties().get("reset_qubit");
                boolean isRegister = lastDef.keySet().stream().anyMatch(key -> key.startsWith(resetQubit + "["));

                if (isRegister) {
                    for (String key : lastDef.keySet()) {
                        if (key.startsWith(resetQubit + "[")) {
                            edges.add(edge(lastDef.get(key), n, "PDG_DATA", "data_dep_reset"));
                        }
                    }
                } else {
                    if (lastDef.containsKey(resetQubit)) {
                        edges.add(edge(lastDef.get(resetQubit), n, "PDG_DATA", "data_dep_reset"));
                    }
                }
            } else if ("BARRIER_STATEMENT".equals(descriptiveType)) {
                String barrierTargets = (String) n.getProperties().get("barrier_operands");
                boolean isRegister = lastDef.keySet().stream().anyMatch(key -> key.startsWith(barrierTargets + "["));

                if (isRegister) {
                    for (String key : lastDef.keySet()) {
                        if (key.startsWith(barrierTargets + "[")) {
                            edges.add(edge(lastDef.get(key), n, "PDG_DATA", "data_dep_barrier"));
                        }
                    }
                } else {
                    for (String target : barrierTargets.split(",")) {
                        String normalizedTarget = target.trim();
                        if (lastDef.containsKey(normalizedTarget)) {
                            edges.add(edge(lastDef.get(normalizedTarget), n, "PDG_DATA", "data_dep_barrier"));
                        }
                    }
                }
            }
        }

        // Create control dependency edges for if and loop statements.
        for (AstNode c : filtered) {
            if (isIfStatement(c) || isLoopStatement(c)) {
                for (AstNode s : subsequentFilteredStatements(filtered, c)) {
                    edges.add(edge(c, s, "PDG_CONTROL", "control_dep"));
                }
            }
        }

        return edges;
    }

    // Checks if the given AST node has any label that is part of the allowed
    // labels.
    private boolean hasAllowedLabel(AstNode n) {
        for (String lab : n.getLabels()) {
            if (ALLOWED_LABELS.contains(lab.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    // Collects all AST nodes from the subtree rooted at the given node and adds
    // them to the list.
    private void collectNodes(AstNode node, List<AstNode> list) {
        if (node == null)
            return;
        list.add(node); // Add the current node to the list.
        for (AstNode c : node.getChildren()) { // Recursively collect child nodes.
            collectNodes(c, list);
        }
    }

    // Retrieves the line number of the given AST node or returns a default value if
    // not available.
    private long lineNumber(AstNode n) {
        Number ln = (Number) n.getProperties().getOrDefault("line_of_code", 999999L);
        return ln.longValue();
    }

    // Creates an edge between two nodes with specified relationship type and
    // metadata.
    private EdgeBase edge(NodeBase from, NodeBase to, String relType, String info) {
        Map<String, Object> p = new HashMap<>();
        p.put("rel_type", relType); // Relationship type (e.g., CFG or PDG).
        p.put("info", info); // Additional edge metadata.

        AstEdge e = new AstEdge();
        e.setFrom(from);
        e.setTo(to);
        e.setProperties(p);
        return e;
    }

    // Creates an auxiliary node with the given label and information.
    private AstNode createAuxNode(String label, String info) {
        AstNode n = new AstNode();
        n.setId(Math.abs(new Random().nextLong())); // Generate a unique ID.
        n.getLabels().add(label); // Assign the label to the node.

        Map<String, Object> p = new HashMap<>();
        p.put("info", info);
        p.put("file", this.currentFile); // Associate with the current file.
        p.put("author", "system");
        p.put("version", "1.0");
        n.setProperties(p);
        return n;
    }

    // Checks if the given AST node represents an IF statement.
    private boolean isIfStatement(AstNode node) {
        String d = (String) node.getProperties().getOrDefault("descriptive_type", "");
        return d.equalsIgnoreCase("IF_STATEMENT");
    }

    // Checks if the given AST node represents a loop statement (FOR or WHILE).
    private boolean isLoopStatement(AstNode node) {
        String d = (String) node.getProperties().getOrDefault("descriptive_type", "");
        d = d.toLowerCase();
        return d.contains("for_loop") || d.contains("while_loop");
    }

    // Tracks variable definitions for dependency tracking in the PDG.
    private void defineVar(AstNode n, Map<String, AstNode> lastDef) {
        String descriptiveType = (String) n.getProperties().getOrDefault("descriptive_type", "");
        String d = (String) n.getProperties().getOrDefault("descriptive_type", "");

        if ("QUBIT_DECLARATION".equals(descriptiveType)) {
            String qubitName = (String) n.getProperties().get("qubit_name");
            int size = Integer.parseInt((String) n.getProperties().getOrDefault("qubit_size", "1"));
            for (int i = 0; i < size; i++) {
                lastDef.put(qubitName + "[" + i + "]", n);
            }
            lastDef.put(qubitName, n);
        } else if ("BIT_DECLARATION".equals(descriptiveType)) {
            String bitName = (String) n.getProperties().get("bit_name");
            int size = Integer.parseInt((String) n.getProperties().getOrDefault("bit_size", "1"));
            for (int i = 0; i < size; i++) {
                lastDef.put(bitName + "[" + i + "]", n);
            }
        } else if (d.equals("CLASSICAL_DECLARATION")) {
            String var = (String) n.getProperties().get("var_name");
            if (var != null && !var.isEmpty())
                lastDef.put(var, n);
        } else if ("INDEX_ASSIGNMENT".equals(descriptiveType)) {
            String target = (String) n.getProperties().get("assigned_array") + "["
                    + n.getProperties().get("assigned_index") + "]";
            lastDef.put(target, n);
        } else if (d.equals("CONST_DECLARATION")) {
            String constName = (String) n.getProperties().get("const_name");
            if (constName != null && !constName.isEmpty())
                lastDef.put(constName, n);
        } else if (d.equals("ARRAY_DECLARATION")) {
            String arrayName = (String) n.getProperties().get("array_name");
            if (arrayName != null && !arrayName.isEmpty())
                lastDef.put(arrayName, n);
        } else if (d.equals("FOR_LOOP")) {
            String loopVar = (String) n.getProperties().get("loop_variable");
            if (loopVar != null && !loopVar.isEmpty())
                lastDef.put(loopVar, n);
        } else if (d.equals("GATE_DEFINITION")) {
            String gateName = (String) n.getProperties().get("gate_name");
            if (gateName != null && !gateName.isEmpty())
                lastDef.put(gateName, n);
        }
    }

    // Finds subsequent statements with a higher line number for control
    // dependencies.
    private List<AstNode> subsequentFilteredStatements(List<AstNode> all, AstNode cond) {
        List<AstNode> res = new ArrayList<>();
        long cl = lineNumber(cond);
        for (AstNode s : all) {
            if (s != cond && lineNumber(s) > cl) {
                res.add(s); // Add nodes with a higher line number.
            }
        }
        return res;
    }

    // Creates a condition node (e.g., for IF or LOOP conditions) with metadata.
    private AstNode createConditionNode(String nodeType, String label) {
        AstNode c = new AstNode();
        c.setId(Math.abs(new Random().nextLong())); // Generate a unique ID.
        c.getLabels().add(label); // Assign the label to the node.

        Map<String, Object> p = new HashMap<>();
        p.put("info", nodeType);
        p.put("node_type", nodeType);
        c.setProperties(p);
        return c;
    }
}
