package com.qcpg.qcpg.model.graphCreation;

import lombok.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an edge in the Program Dependency Graph (PDG) of the Code Property
 * Graph (CPG).
 * This edge connects two PDG nodes (`from` and `to`) and includes labels and
 * properties
 * to describe its characteristics and relationships.
 */
@Data
@Builder
public class PdgEdge implements EdgeBase {

    /** The source node of the PDG edge. */
    private PdgNode from;

    /** The target node of the PDG edge. */
    private PdgNode to;

    /** Labels describing the type or role of the edge in the PDG. */
    @Builder.Default
    private List<String> labels = new ArrayList<>();

    /**
     * Key-value properties providing metadata or additional details about the edge.
     */
    @Builder.Default
    private Map<String, Object> properties = Map.of();

    /**
     * Retrieves the source node of the PDG edge.
     * 
     * @return The source node as a `NodeBase` instance.
     */
    @Override
    public NodeBase getFrom() {
        return from;
    }

    /**
     * Retrieves the target node of the PDG edge.
     * 
     * @return The target node as a `NodeBase` instance.
     */
    @Override
    public NodeBase getTo() {
        return to;
    }
}
