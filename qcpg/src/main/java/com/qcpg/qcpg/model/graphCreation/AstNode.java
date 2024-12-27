package com.qcpg.qcpg.model.graphCreation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a node in the Abstract Syntax Tree (AST) of the Code Property
 * Graph (CPG).
 * Each node contains unique identifiers, labels, properties, and child nodes,
 * enabling hierarchical representation of a program's structure.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AstNode implements NodeBase {

    /** Unique identifier for the node. */
    private long id;

    /** Labels describing the type or role of the node. */
    @Builder.Default
    private List<String> labels = new ArrayList<>();

    /**
     * Key-value properties providing metadata or additional details about the node.
     */
    @Builder.Default
    private Map<String, Object> properties = Map.of();

    /** List of child nodes representing the hierarchical structure of the AST. */
    @Builder.Default
    private List<AstNode> children = new ArrayList<>();

    /**
     * Retrieves the type of the node, which in this case is always `AST`.
     * 
     * @return The type of the node as `NodeBaseType.AST`.
     */
    @Override
    public NodeBaseType getType() {
        return NodeBaseType.AST;
    }

    /**
     * Updates the properties of the node with the provided key-value map.
     * 
     * @param props A map containing new properties to set on the node.
     */
    @Override
    public void setProperties(Map<String, Object> props) {
        this.properties = props;
    }
}
