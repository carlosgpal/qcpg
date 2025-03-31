package com.qcpg.qcpg.model.graphCreation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents an edge in the Abstract Syntax Tree (AST) for the Code Property
 * Graph (CPG).
 * This edge connects two nodes (`from` and `to`) and can have labels and
 * properties
 * to describe its characteristics and relationships.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AstEdge implements EdgeBase {

    /** The source node of the edge. */
    private NodeBase from;

    /** The target node of the edge. */
    private NodeBase to;

    /** List of labels to describe the edge type. */
    @Builder.Default
    private List<String> labels = new ArrayList<>();

    /**
     * Map of properties providing metadata or additional details about the edge.
     */
    @Builder.Default
    private Map<String, Object> properties = Map.of();

    /**
     * Retrieves the source node of the edge.
     * 
     * @return the node where the edge starts.
     */
    @Override
    public NodeBase getFrom() {
        return from;
    }

    /**
     * Retrieves the target node of the edge.
     * 
     * @return the node where the edge ends.
     */
    @Override
    public NodeBase getTo() {
        return to;
    }

    /**
     * Builder class for constructing `AstEdge` instances with fluent API.
     */
    public static class AstEdgeBuilder {
        /**
         * Sets the source node (`from`) for the edge.
         *
         * @param n the source node.
         * @return the updated `AstEdgeBuilder` instance.
         */
        public AstEdgeBuilder from(NodeBase n) {
            this.from = n;
            return this;
        }

        /**
         * Sets the target node (`to`) for the edge.
         *
         * @param n the target node.
         * @return the updated `AstEdgeBuilder` instance.
         */
        public AstEdgeBuilder to(NodeBase n) {
            this.to = n;
            return this;
        }
    }
}
