package com.qcpg.qcpg.repository.graphCreation;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qcpg.qcpg.model.graphAnalysis.GenericNode;

/**
 * Repository interface for managing graph creation and operations in a Neo4j
 * database.
 * Provides methods to clear the database and insert nodes and edges from CSV
 * files.
 */
@Repository
public interface GraphCreationRepository extends Neo4jRepository<GenericNode, Long> {

       /**
        * Clears the entire Neo4j database by detaching and deleting all nodes.
        */
       @Query("MATCH (n) detach delete n")
       void clearDB();

       /**
        * Inserts nodes into the Neo4j database from a specified CSV file.
        * Each line in the CSV represents a node with properties and labels.
        * 
        * @param filePath The file path of the CSV containing node data.
        */
       @Query("LOAD CSV WITH HEADERS FROM $filePath AS line " +
                     "WITH line, apoc.convert.fromJsonList(line.labels) AS labList, apoc.convert.fromJsonMap(line.properties) AS props "
                     +
                     "CREATE (n) " +
                     "SET n.id = toInteger(line.id) " +
                     "SET n += props " +
                     "WITH n, labList " +
                     "CALL apoc.create.addLabels(n, labList) YIELD node " +
                     "RETURN count(*) AS nodesCreated;")
       void insertNodesFromCsv(@Param("filePath") String filePath);

       /**
        * Inserts edges into the Neo4j database from a specified CSV file.
        * Each line in the CSV represents an edge with properties and connects nodes
        * using their IDs.
        * 
        * @param filePath The file path of the CSV containing edge data.
        */
       @Query("LOAD CSV WITH HEADERS FROM $filePath AS line " +
                     "WITH line, apoc.convert.fromJsonMap(line.properties) AS eprops " +
                     "MATCH (start {id: toInteger(line.start_id)}), (end {id: toInteger(line.end_id)}) " +
                     "CALL apoc.create.relationship(start, eprops.rel_type, eprops, end) YIELD rel " +
                     "RETURN count(*) AS edgesCreated;")
       void insertEdgesFromCsv(@Param("filePath") String filePath);
}
