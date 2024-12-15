package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.graph.AstNode;
import com.qcpg.qcpg.graph.CfgGraph;
import com.qcpg.qcpg.graph.CfgNode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class QasmCfgBuilder {

    private static final AtomicLong ID_GEN = new AtomicLong(1);

    private CfgNode createNode(String baseName) {
        CfgNode node = CfgNode.builder().id(ID_GEN.getAndIncrement()).build();
        assignNodeData(node, baseName);
        return node;
    }

    private void assignNodeData(CfgNode node, String baseName) {
        node.getLabels().add(baseName.toUpperCase());
        node.getLabels().add(baseName.toUpperCase()+"_EXTRA");
        Map<String,Object> props = new HashMap<>();
        props.put("author", "carlo_cfg");
        props.put("version", "2.0");
        props.put("info", baseName + "_cfg_node_info");
        node.setProperties(props);
    }

    public CfgGraph buildCFG(AstNode root) {
        CfgGraph cfg = CfgGraph.builder().build();

        CfgNode entry = createNode("ENTRY");
        cfg.setEntryNode(entry);
        cfg.getAllNodes().add(entry);

        CfgNode current = entry;

        for (AstNode child : root.getChildren()) {
            if (child.getLabels().contains("GLOBAL_STATEMENT")) {
                CfgNode stmtNode = createNode("GLOBAL_STATEMENT");
                cfg.addNode(stmtNode);
                current.getSuccessors().add(stmtNode);
                current = stmtNode;
            }
        }

        CfgNode exit = createNode("EXIT");
        cfg.addNode(exit);
        current.getSuccessors().add(exit);

        return cfg;
    }
}
