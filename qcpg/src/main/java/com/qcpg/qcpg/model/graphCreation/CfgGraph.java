package com.qcpg.qcpg.model.graphCreation;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Control Flow Graph (CFG) as part of the Code Property Graph
 * (CPG).
 * The graph starts with an entry node and contains a list of all nodes
 * that form the structure of the CFG.
 */
@Data
@Builder
public class CfgGraph {

    /** The entry point node of the CFG. */
    private CfgNode entryNode;

    /** A list of all nodes present in the CFG. */
    @Builder.Default
    private List<CfgNode> allNodes = new ArrayList<>();

    /**
     * Adds a node to the list of nodes in the CFG.
     * 
     * @param node The `CfgNode` to be added to the graph.
     */
    public void addNode(CfgNode node) {
        allNodes.add(node);
    }
}
