package com.qcpg.qcpg.graph;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CfgGraph {
    private CfgNode entryNode;
    @Builder.Default
    private List<CfgNode> allNodes = new ArrayList<>();

    public void addNode(CfgNode node) {
        if (!allNodes.contains(node)) {
            allNodes.add(node);
        }
    }
}
