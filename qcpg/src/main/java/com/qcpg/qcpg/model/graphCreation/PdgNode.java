package com.qcpg.qcpg.model.graphCreation;

import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a node in the Program Dependency Graph (PDG) of the Code Property
 * Graph (CPG).
 * Each node captures specific information about data or control dependencies
 * within the program.
 */
@Data
@Builder
public class PdgNode implements NodeBase {

    /** Unique identifier for the PDG node. */
    private long id;

    /** Labels describing the type or role of the node in the PDG. */
    @Builder.Default
    private List<String> labels = new ArrayList<>();

    /**
     * Key-value properties providing metadata or additional details about the node.
     */
    @Builder.Default
    private Map<String, Object> properties = Map.of();

    /**
     * Retrieves the type of the node, which is always `PDG` for this
     * implementation.
     * 
     * @return The type of the node as `NodeBaseType.PDG`.
     */
    @Override
    public NodeBaseType getType() {
        return NodeBaseType.PDG;
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
