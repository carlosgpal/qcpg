package com.qcpg.qcpg.model.graphCreation;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Program Dependency Graph (PDG) as part of the Code Property
 * Graph (CPG).
 * The graph consists of nodes and edges that capture data and control
 * dependencies
 * within a program.
 */
@Data
@Builder
public class PdgGraph {

    /**
     * A list of nodes in the PDG. Each node represents an element of program
     * dependency.
     */
    @Builder.Default
    private List<PdgNode> nodes = new ArrayList<>();

    /**
     * A list of edges in the PDG. Each edge represents a dependency between nodes.
     */
    @Builder.Default
    private List<PdgEdge> edges = new ArrayList<>();
}
