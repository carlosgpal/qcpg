package com.qcpg.qcpg.repository;

import com.qcpg.qcpg.entities.GenericNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GenericNodeRepository extends Neo4jRepository<GenericNode, Long> {
}
