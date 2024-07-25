package com.qcpg.qcpg.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@NoArgsConstructor
@Node("GenericNode")
public class GenericNode {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String name;
    private String value;

    @Relationship
    private List<GenericRelationship> relationships;
}