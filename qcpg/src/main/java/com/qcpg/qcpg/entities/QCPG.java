package com.qcpg.qcpg.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Data
@NoArgsConstructor
@Node
public class QCPG {

    @Id
    @GeneratedValue
    private Long id;
    private String filename;
    // CODE del file o poner el propio file ns depende de cassandra
    private List<GenericNode> nodes;
}
