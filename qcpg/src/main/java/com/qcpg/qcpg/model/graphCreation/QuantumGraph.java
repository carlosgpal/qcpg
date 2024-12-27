package com.qcpg.qcpg.model.graphCreation;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the quantum-specific part of the Code Property Graph (CPG).
 * This graph includes quantum nodes and edges that capture quantum operations
 * and their relationships.
 */
@Data
@Builder
public class QuantumGraph {

    /**
     * List of quantum nodes in the graph. Each node represents a quantum operation
     * or state.
     */
    private final List<NodeBase> quantumNodes;

    /**
     * List of quantum edges in the graph. Each edge represents a relationship
     * between quantum nodes.
     */
    private final List<EdgeBase> quantumEdges;

    /**
     * Constructs a `QuantumGraph` with the specified nodes and edges.
     *
     * @param quantumNodes A list of quantum-specific nodes.
     * @param quantumEdges A list of quantum-specific edges.
     */
    public QuantumGraph(List<NodeBase> quantumNodes, List<EdgeBase> quantumEdges) {
        this.quantumNodes = quantumNodes;
        this.quantumEdges = quantumEdges;
    }

    /**
     * Retrieves the list of quantum nodes in the graph.
     *
     * @return A list of nodes representing quantum operations or states.
     */
    public List<NodeBase> getQuantumNodes() {
        return quantumNodes;
    }

    /**
     * Retrieves the list of quantum edges in the graph.
     *
     * @return A list of edges representing relationships between quantum nodes.
     */
    public List<EdgeBase> getQuantumEdges() {
        return quantumEdges;
    }
}
