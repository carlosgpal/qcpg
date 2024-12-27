package com.qcpg.qcpg.model.graphCreation;

import java.util.List;
import java.util.Map;

/**
 * Represents the base interface for all edge types in the Code Property Graph
 * (CPG).
 * Provides methods to retrieve the source and target nodes, labels, and
 * properties of the edge.
 */
public interface EdgeBase {

    /**
     * Retrieves the source node of the edge.
     *
     * @return The `NodeBase` instance representing the source of the edge.
     */
    NodeBase getFrom();

    /**
     * Retrieves the target node of the edge.
     *
     * @return The `NodeBase` instance representing the target of the edge.
     */
    NodeBase getTo();

    /**
     * Retrieves the labels associated with the edge.
     * Labels describe the type or role of the edge in the graph.
     *
     * @return A list of labels for the edge.
     */
    List<String> getLabels();

    /**
     * Retrieves the properties of the edge.
     * Properties provide metadata or additional information about the edge.
     *
     * @return A map containing key-value pairs representing the edge's properties.
     */
    Map<String, Object> getProperties();
}
