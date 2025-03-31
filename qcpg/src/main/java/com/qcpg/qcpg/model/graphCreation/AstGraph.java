package com.qcpg.qcpg.model.graphCreation;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Abstract Syntax Tree (AST) graph structure in the Code
 * Property Graph (CPG).
 * This graph starts with a root node and allows traversal and collection of all
 * nodes in the tree.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AstGraph {

    /** The root node of the AST graph. */
    private AstNode root;

    /**
     * Retrieves all nodes present in the AST graph by performing a traversal
     * starting from the root node.
     * 
     * @return a list of all nodes in the AST graph.
     */
    public List<AstNode> getAllNodes() {
        List<AstNode> nodes = new ArrayList<>();
        collectNodes(root, nodes);
        return nodes;
    }

    /**
     * Recursively collects all nodes in the tree starting from the specified node.
     * Adds each visited node to the provided container list.
     * 
     * @param node      The current node being traversed.
     * @param container The list to store the collected nodes.
     */
    private void collectNodes(AstNode node, List<AstNode> container) {
        if (node == null)
            return; // Base case for recursion: null node.
        container.add(node); // Add the current node to the container.
        for (AstNode c : node.getChildren()) { // Traverse each child node.
            collectNodes(c, container);
        }
    }
}
