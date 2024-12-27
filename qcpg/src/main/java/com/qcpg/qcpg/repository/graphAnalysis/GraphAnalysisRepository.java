package com.qcpg.qcpg.repository.graphAnalysis;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.qcpg.qcpg.model.graphAnalysis.*;

import java.util.List;

/**
 * Este repositorio se ha modificado conceptualmente para ajustarse a la nueva estructura del AST y a la falta de elementos cuánticos o PDG/CFG avanzados.
 * 
 * Ahora tenemos nodos con etiquetas como:
 * - PROGRAM_ROOT (para el nodo raíz)
 * - AST_NODE
 * - DECLARATION, STATEMENT, EXPRESSION, etc.
 * 
 * Y relaciones de tipo:
 * - :AST (para la jerarquía del AST)
 * 
 * Debido a que el CFG y PDG actuales son limitados o inexistentes, las queries relacionadas a ellos se simplifican a devolver conjuntos vacíos o nodos básicos si existiesen.
 * 
 * Las queries cuánticas y de análisis avanzado (QuantumBit, QuantumGate, etc.) se simplifican también. Ahora serán placehoders vacíos o minimalistas.
 */
@Repository
public interface GraphAnalysisRepository extends Neo4jRepository<GenericNode, Long> {

    @Query("MATCH (root:PROGRAM_ROOT) " +
           "OPTIONAL MATCH (root)-[:AST*]->(child) " +
           "WITH root, COLLECT(DISTINCT child) AS children " +
           "UNWIND [root] + children AS node " +
           "RETURN DISTINCT id(node) AS id, node.display_name AS fullName, node.file AS file, node.code_line AS code, labels(node) AS labels")
    List<GenericNode> getAstNodes();

    @Query("MATCH (sourceNode)-[r:AST]->(targetNode) " +
           "WHERE id(sourceNode) IN $selectedNodeIds " +
           "AND id(targetNode) IN $selectedNodeIds " +
           "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getAstRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (node) WHERE 'CFG_ENTRY' IN labels(node) OR 'CFG_EXIT' IN labels(node) OR 'STATEMENT' IN labels(node) " +
           "RETURN DISTINCT id(node) AS id, node.display_name AS fullName, node.file AS file, node.code_line AS code, labels(node) AS labels")
    List<GenericNode> getCfgNodes();

    @Query("MATCH (sourceNode)-[r:CFG|FLOWS_TO*]->(targetNode) " +
           "WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds " +
           "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getCfgRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (node) WHERE 'PDG' IN labels(node) RETURN DISTINCT id(node) AS id, node.display_name AS fullName, node.file AS file, node.code_line AS code, labels(node) AS labels")
    List<GenericNode> getPdgNodes();

    @Query("MATCH (sourceNode)-[r:PDG|PDG_CONTROL|PDG_TRUE|PDG_FALSE]->(targetNode) " +
           "WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds " +
           "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getPdgRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (n:QUBIT_DECLARATION) RETURN n")
    List<GenericNode> getNumQubits();

    @Query("MATCH (n:BIT_DECLARATION) RETURN n")
    List<GenericNode> getNumClassicBits();

    @Query("RETURN [] AS n")
    List<GenericNode> getMappingBitsNodes();

    @Query("RETURN [] AS rel")
    List<GenericRelationship> getMappingBitsRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (n) WHERE 'GATE_CALL' IN labels(n) RETURN n")
    List<GenericNode> getNumGates();

    @Query("RETURN [] AS n")
    List<GenericNode> getMappingGatesNodes();

    @Query("RETURN [] AS rel")
    List<GenericRelationship> getMappingGatesRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (n:MEASURE) RETURN n")
    List<GenericNode> getNumMeasures();

    @Query("RETURN [] AS n")
    List<GenericNode> getMappingMeasuresNodes();

    @Query("RETURN [] AS rel")
    List<GenericRelationship> getMappingMeasuresRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (n) WHERE n.file IS NOT NULL " +
           "WITH n.file AS file, COLLECT(n)[0] AS node " +
           "RETURN id(node) AS id, node.display_name AS fullName, node.file AS file, node.code_line AS code, labels(node) AS labels")
    List<GenericNode> getFiles();

    @Query("RETURN [] AS n")
    List<GenericNode> getStatePreparationNodes();

    @Query("RETURN [] AS rel")
    List<GenericRelationship> getStatePreparationRelationships(List<Long> selectedNodeIds);

    @Query("RETURN [] AS n")
    List<GenericNode> getUniformSuperpositionNodes();

    @Query("RETURN [] AS rel")
    List<GenericRelationship> getUniformSuperpositionRelationships(List<Long> selectedNodeIds);

    @Query("RETURN [] AS n")
    List<GenericNode> getCreatingEntanglementNodes();

    @Query("RETURN [] AS rel")
    List<GenericRelationship> getCreatingEntanglementRelationships(List<Long> selectedNodeIds);
}
