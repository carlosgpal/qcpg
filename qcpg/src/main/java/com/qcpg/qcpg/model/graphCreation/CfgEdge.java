package com.qcpg.qcpg.model.graphCreation;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents an edge in the Control Flow Graph (CFG) of the Code Property Graph
 * (CPG).
 * This edge connects two CFG nodes (`from` and `to`) and contains labels and
 * properties
 * to describe its characteristics and relationships.
 */
@Data
@Builder
public class CfgEdge implements EdgeBase {

    /** The source node of the CFG edge. */
    private CfgNode from;

    /** The target node of the CFG edge. */
    private CfgNode to;

    /** Labels describing the type or role of the edge in the CFG. */
    @Builder.Default
    private List<String> labels = new ArrayList<>();

    /**
     * Key-value properties providing metadata or additional details about the edge.
     */
    @Builder.Default
    private Map<String, Object> properties = Map.of();

    /**
     * Retrieves the source node of the CFG edge.
     * 
     * @return The source node of the edge.
     */
    @Override
    public NodeBase getFrom() {
        return from;
    }

    /**
     * Retrieves the target node of the CFG edge.
     * 
     * @return The target node of the edge.
     */
    @Override
    public NodeBase getTo() {
        return to;
    }
}
