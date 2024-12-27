package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.model.graphCreation.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service for building the quantum-specific part of the Code Property Graph
 * (CPG).
 * Handles the creation of quantum nodes (e.g., qubits, gates, measurements) and
 * their edges.
 */
@Service
public class QasmQuantumGraphBuilder {

    private static final AtomicLong ID_GEN = new AtomicLong(1); // Global ID generator for nodes.

    /**
     * Builds the quantum graph (QCPG) from a list of AST nodes and their associated
     * code lines.
     *
     * @param codeLines The QASM code as a list of lines.
     * @param astNodes  The AST nodes to process for quantum graph construction.
     * @return A QuantumGraph containing quantum-specific nodes and edges.
     */
    public QuantumGraph buildQuantumGraph(List<String> codeLines, List<AstNode> astNodes) {
        List<NodeBase> quantumNodes = new ArrayList<>();
        List<EdgeBase> quantumEdges = new ArrayList<>();

        Map<String, NodeBase> qubitNodes = new HashMap<>(); // Map to store qubit nodes by name.
        Map<String, NodeBase> bitNodes = new HashMap<>(); // Map to store classical bit nodes by name.

        // Process qubit and bit declarations to create their nodes.
        for (AstNode astNode : astNodes) {
            String descriptiveType = (String) astNode.getProperties().get("descriptive_type");

            if ("QUBIT_DECLARATION".equals(descriptiveType)) {
                int size = Integer.parseInt((String) astNode.getProperties().get("qubit_size"));
                String qubitName = (String) astNode.getProperties().get("qubit_name");
                for (int i = 0; i < size; i++) {
                    String qubitKey = qubitName + "[" + i + "]";
                    NodeBase qubitNode = createNode("QUANTUM_QUBIT", qubitKey, astNode);
                    qubitNodes.put(qubitKey, qubitNode);
                    quantumNodes.add(qubitNode);
                    createReferenceEdge(quantumEdges, qubitNode, astNode);
                }
            } else if ("BIT_DECLARATION".equals(descriptiveType)) {
                int size = Integer.parseInt((String) astNode.getProperties().get("bit_size"));
                String bitName = (String) astNode.getProperties().get("bit_name");
                for (int i = 0; i < size; i++) {
                    String bitKey = bitName + "[" + i + "]";
                    NodeBase bitNode = createNode("CLASSICAL_BIT", bitKey, astNode);
                    bitNodes.put(bitKey, bitNode);
                    quantumNodes.add(bitNode);
                    createReferenceEdge(quantumEdges, bitNode, astNode);
                }
            }
        }

        // Process operations such as gates, measurements, resets, and barriers.
        NodeBase lastNode = null;
        for (AstNode astNode : astNodes) {
            String descriptiveType = (String) astNode.getProperties().get("descriptive_type");

            if ("GATE_CALL".equals(descriptiveType)) {
                processGateCall(quantumEdges, quantumNodes, qubitNodes, astNode);
                lastNode = addExecutionOrderEdge(quantumEdges, lastNode, quantumNodes.get(quantumNodes.size() - 1));
            } else if ("MEASURE_CALL".equals(descriptiveType)) {
                processMeasureCall(quantumEdges, quantumNodes, qubitNodes, bitNodes, astNode);
                lastNode = addExecutionOrderEdge(quantumEdges, lastNode, quantumNodes.get(quantumNodes.size() - 1));
            } else if ("RESET_STATEMENT".equals(descriptiveType)) {
                processResetStatement(quantumEdges, quantumNodes, qubitNodes, astNode);
                lastNode = addExecutionOrderEdge(quantumEdges, lastNode, quantumNodes.get(quantumNodes.size() - 1));
            } else if ("BARRIER_STATEMENT".equals(descriptiveType)) {
                processBarrierStatement(quantumEdges, quantumNodes, qubitNodes, astNode);
                lastNode = addExecutionOrderEdge(quantumEdges, lastNode, quantumNodes.get(quantumNodes.size() - 1));
            } else if ("IF_STATEMENT".equals(descriptiveType) || "FOR_LOOP".equals(descriptiveType)) {
                processConditionalOrLoop(quantumEdges, quantumNodes, astNode);
                lastNode = addExecutionOrderEdge(quantumEdges, lastNode, quantumNodes.get(quantumNodes.size() - 1));
            }
        }

        return new QuantumGraph(quantumNodes, quantumEdges);
    }

    // Helper methods for processing specific operations.

    private void processGateCall(List<EdgeBase> quantumEdges, List<NodeBase> quantumNodes,
            Map<String, NodeBase> qubitNodes, AstNode astNode) {
        String gateName = (String) astNode.getProperties().get("gate_name");
        String targetQubit = (String) astNode.getProperties().get("target_qubit");
        String targetIndex = (String) astNode.getProperties().get("target_index");

        String normalizedTarget = targetQubit + "[" + targetIndex + "]";
        NodeBase gateNode = createNodeWithLabels("QUANTUM_GATE", "QUANTUM_GATE_" + gateName.toUpperCase(), gateName,
                astNode);
        quantumNodes.add(gateNode);
        createReferenceEdge(quantumEdges, gateNode, astNode);

        if (qubitNodes.containsKey(normalizedTarget)) {
            quantumEdges.add(
                    createEdgeWithRole(gateNode, qubitNodes.get(normalizedTarget), "QUANTUM_GATE_OPERAND", "target"));
        }
    }

    private void processMeasureCall(List<EdgeBase> quantumEdges, List<NodeBase> quantumNodes,
            Map<String, NodeBase> qubitNodes, Map<String, NodeBase> bitNodes, AstNode astNode) {
        String measuredQubit = Optional.ofNullable((String) astNode.getProperties().get("measured_qubit")).orElse("");
        String measuredQubitIndex = Optional.ofNullable((String) astNode.getProperties().get("measured_qubit_index"))
                .orElse("0");
        String destinationBit = Optional.ofNullable((String) astNode.getProperties().get("destination_bit")).orElse("");
        String destinationBitIndex = Optional.ofNullable((String) astNode.getProperties().get("destination_bit_index"))
                .orElse("0");

        String measuredQubitKey = measuredQubit + "[" + measuredQubitIndex + "]";
        String destinationBitKey = destinationBit + "[" + destinationBitIndex + "]";

        if (qubitNodes.containsKey(measuredQubitKey) && bitNodes.containsKey(destinationBitKey)) {
            NodeBase measureNode = createNode("QUANTUM_MEASURE", "Measure", astNode);
            quantumNodes.add(measureNode);

            quantumEdges.add(createEdge(measureNode, qubitNodes.get(measuredQubitKey), "MEASUREMENT_SOURCE"));
            quantumEdges.add(createEdge(measureNode, bitNodes.get(destinationBitKey), "MEASUREMENT_RESULT"));
            createReferenceEdge(quantumEdges, measureNode, astNode);
        }
    }

    private void processResetStatement(List<EdgeBase> quantumEdges, List<NodeBase> quantumNodes,
            Map<String, NodeBase> qubitNodes, AstNode astNode) {
        String resetTarget = Optional.ofNullable((String) astNode.getProperties().get("reset_qubit")).orElse("");
        if (qubitNodes.containsKey(resetTarget)) {
            NodeBase resetNode = createNode("QUANTUM_RESET", resetTarget, astNode);
            quantumNodes.add(resetNode);
            quantumEdges.add(createEdge(resetNode, qubitNodes.get(resetTarget), "QUANTUM_DEPENDENCY"));
            createReferenceEdge(quantumEdges, resetNode, astNode);
        }
    }

    private void processBarrierStatement(List<EdgeBase> quantumEdges, List<NodeBase> quantumNodes,
            Map<String, NodeBase> qubitNodes, AstNode astNode) {
        String barrierTargets = Optional.ofNullable((String) astNode.getProperties().get("barrier_operands"))
                .orElse("");
        NodeBase barrierNode = createNode("QUANTUM_BARRIER", "Barrier", astNode);
        quantumNodes.add(barrierNode);
        createReferenceEdge(quantumEdges, barrierNode, astNode);

        for (String target : barrierTargets.split(",")) {
            target = target.trim();
            if (qubitNodes.containsKey(target)) {
                quantumEdges.add(createEdge(barrierNode, qubitNodes.get(target), "QUANTUM_DEPENDENCY"));
            }
        }
    }

    private void processConditionalOrLoop(List<EdgeBase> quantumEdges, List<NodeBase> quantumNodes, AstNode astNode) {
        NodeBase conditionalNode = createNode("CONDITIONAL_NODE",
                (String) astNode.getProperties().get("descriptive_type"), astNode);
        quantumNodes.add(conditionalNode);
        createReferenceEdge(quantumEdges, conditionalNode, astNode);
    }

    // Node and edge creation helper methods.

    private NodeBase createNode(String type, String name, AstNode referenceNode) {
        return AstNode.builder()
                .id(ID_GEN.getAndIncrement())
                .labels(List.of(type))
                .properties(Map.of(
                        "name", name,
                        "reference_node_id", referenceNode.getId()))
                .build();
    }

    private NodeBase createNodeWithLabels(String baseLabel, String descriptiveLabel, String name,
            AstNode referenceNode) {
        return AstNode.builder()
                .id(ID_GEN.getAndIncrement())
                .labels(List.of(baseLabel, descriptiveLabel))
                .properties(Map.of(
                        "name", name,
                        "reference_node_id", referenceNode.getId()))
                .build();
    }

    private void createReferenceEdge(List<EdgeBase> edges, NodeBase from, AstNode to) {
        edges.add(AstEdge.builder()
                .from(from)
                .to(to)
                .properties(Map.of(
                        "rel_type", "REFERENCE",
                        "info", "Refers to AST node"))
                .build());
    }

    private EdgeBase createEdge(NodeBase from, NodeBase to, String relType) {
        return AstEdge.builder()
                .from(from)
                .to(to)
                .properties(Map.of(
                        "rel_type", relType))
                .build();
    }

    private EdgeBase createEdgeWithRole(NodeBase from, NodeBase to, String relType, String role) {
        return AstEdge.builder()
                .from(from)
                .to(to)
                .properties(Map.of(
                        "rel_type", relType,
                        "role", role))
                .build();
    }

    private NodeBase addExecutionOrderEdge(List<EdgeBase> edges, NodeBase lastNode, NodeBase currentNode) {
        if (lastNode != null) {
            edges.add(createEdge(lastNode, currentNode, "EXECUTION_ORDER"));
        }
        return currentNode;
    }
}
