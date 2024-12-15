package com.qcpg.qcpg.graph;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdgGraph {
    @Builder.Default
    private List<PdgNode> nodes = new ArrayList<>();
    @Builder.Default
    private List<PdgEdge> edges = new ArrayList<>();

    public void addNode(PdgNode node) {
        nodes.add(node);
    }

    public void addEdge(PdgEdge edge) {
        edges.add(edge);
    }
}
