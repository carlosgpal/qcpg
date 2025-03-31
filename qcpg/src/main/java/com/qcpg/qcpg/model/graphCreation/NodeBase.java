package com.qcpg.qcpg.model.graphCreation;

import java.util.List;
import java.util.Map;

/**
 * Represents the base interface for all node types in the Code Property Graph
 * (CPG).
 * Provides methods to manage the node's identifiers, labels, properties, and
 * type.
 */
public interface NodeBase {

    /**
     * Retrieves the unique identifier of the node.
     *
     * @return The unique ID of the node as a `long`.
     */
    long getId();

    /**
     * Sets the unique identifier for the node.
     *
     * @param id The unique ID to assign to the node.
     */
    void setId(long id);

    /**
     * Retrieves the labels associated with the node.
     * Labels describe the type or role of the node in the graph.
     *
     * @return A list of labels for the node.
     */
    List<String> getLabels();

    /**
     * Retrieves the properties of the node.
     * Properties provide metadata or additional information about the node.
     *
     * @return A map containing key-value pairs representing the node's properties.
     */
    Map<String, Object> getProperties();

    /**
     * Updates the properties of the node with the provided key-value map.
     *
     * @param props A map containing new properties to set on the node.
     */
    void setProperties(Map<String, Object> props);

    /**
     * Retrieves the type of the node.
     * The type is typically used to classify nodes in the graph (e.g., AST, CFG).
     *
     * @return The `NodeBaseType` representing the type of the node.
     */
    NodeBaseType getType();
}
