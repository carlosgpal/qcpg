package com.qcpg.qcpg.model.graphCreation;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a node in the Control Flow Graph (CFG) of the Code Property Graph
 * (CPG).
 * Each node contains unique identifiers, labels, properties, and successor
 * nodes
 * to define its relationships and control flow structure.
 */
@Data
@Builder
public class CfgNode implements NodeBase {

    /** Unique identifier for the CFG node. */
    private long id;

    /** Labels describing the type or role of the node in the CFG. */
    @Builder.Default
    private List<String> labels = new ArrayList<>();

    /**
     * Key-value properties providing metadata or additional details about the node.
     */
    @Builder.Default
    private Map<String, Object> properties = Map.of();

    /**
     * A list of successor nodes representing outgoing control flow relationships.
     */
    @Builder.Default
    private List<CfgNode> successors = new ArrayList<>();

    /**
     * Retrieves the type of the node, which is always `CFG` for this
     * implementation.
     * 
     * @return The type of the node as `NodeBaseType.CFG`.
     */
    @Override
    public NodeBaseType getType() {
        return NodeBaseType.CFG;
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
