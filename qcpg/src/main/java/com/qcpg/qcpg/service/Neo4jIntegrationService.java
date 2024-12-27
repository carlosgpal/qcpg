package com.qcpg.qcpg.service;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for integrating with a Neo4j database.
 * Provides functionality to connect to Neo4j and execute Cypher queries.
 */
@Service
public class Neo4jIntegrationService {

    private final Driver driver; // Neo4j driver for database connection.

    /**
     * Constructor for Neo4jIntegrationService.
     * Initializes the connection to the Neo4j database using credentials.
     *
     * @param uri      The URI of the Neo4j database.
     * @param username The username for authentication.
     * @param password The password for authentication.
     */
    public Neo4jIntegrationService(
        @Value("${spring.neo4j.uri}") String uri,
        @Value("${spring.neo4j.authentication.username}") String username,
        @Value("${spring.neo4j.authentication.password}") String password
    ) {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    /**
     * Executes a Cypher query in the Neo4j database.
     *
     * @param cypher The Cypher query to execute.
     */
    public void runCypher(String cypher) {
        try (Session session = driver.session()) {
            // Execute the Cypher query in a write transaction.
            session.executeWrite(tx -> {
                tx.run(cypher);
                return null; // No return value needed for this operation.
            });
        }
    }
}
