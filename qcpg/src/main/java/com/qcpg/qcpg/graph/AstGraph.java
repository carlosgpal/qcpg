package com.qcpg.qcpg.graph;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AstGraph {
    private AstNode root;
    
    public List<AstNode> getAllNodes() {
        List<AstNode> nodes = new ArrayList<>();
        if (root != null) {
            collectNodes(root, nodes);
        }
        return nodes;
    }

    private void collectNodes(AstNode node, List<AstNode> list) {
        list.add(node);
        for (AstNode c : node.getChildren()) {
            collectNodes(c, list);
        }
    }
}
