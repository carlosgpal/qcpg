package com.qcpg.qcpg.model.graphAnalysis;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericGraph {
    private List<GenericNode> nodes;
    private List<GenericRelationship> edges;
}
