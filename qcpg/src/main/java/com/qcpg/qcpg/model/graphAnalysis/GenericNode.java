package com.qcpg.qcpg.model.graphAnalysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node("GenericNode")
public class GenericNode {
    @Id
    private Long id;
    @Property("name")
    private String name;
    @Property("file")
    private String file;
    @Property("code")
    private String code;
    @Property("labels")
    private List<String> labels;
}