package com.qcpg.qcpg.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class GenericRelationship {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    @TargetNode
    private GenericNode target;

    public GenericRelationship(String type, GenericNode target) {
        this.type = type;
        this.target = target;
    }
}
