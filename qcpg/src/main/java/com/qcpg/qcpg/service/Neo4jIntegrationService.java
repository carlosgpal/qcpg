package com.qcpg.qcpg.service;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Neo4jIntegrationService {

    private final Driver driver;

    public Neo4jIntegrationService(
        @Value("${spring.neo4j.uri}") String uri,
        @Value("${spring.neo4j.authentication.username}") String username,
        @Value("${spring.neo4j.authentication.password}") String password
    ) {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    public void runCypher(String cypher) {
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                tx.run(cypher);
                return null;
            });
        }
    }
}
