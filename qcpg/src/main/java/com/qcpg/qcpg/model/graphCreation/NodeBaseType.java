package com.qcpg.qcpg.model.graphCreation;

/**
 * Enumeration representing the types of nodes in the Code Property Graph (CPG).
 * Each type corresponds to a specific component of the graph.
 */
public enum NodeBaseType {

    /**
     * Abstract Syntax Tree (AST) node type.
     * Represents the hierarchical structure of source code.
     */
    AST,

    /**
     * Control Flow Graph (CFG) node type.
     * Represents the flow of control in the program's execution.
     */
    CFG,

    /**
     * Program Dependency Graph (PDG) node type.
     * Represents data and control dependencies within the program.
     */
    PDG
}
