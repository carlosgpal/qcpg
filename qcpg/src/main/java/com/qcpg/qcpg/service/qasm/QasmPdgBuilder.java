package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.graph.CfgGraph;
import com.qcpg.qcpg.graph.CfgNode;
import com.qcpg.qcpg.graph.PdgEdge;
import com.qcpg.qcpg.graph.PdgGraph;
import com.qcpg.qcpg.graph.PdgNode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class QasmPdgBuilder {

    private static final AtomicLong NODE_ID = new AtomicLong(1);

    private PdgNode createNode(String baseName) {
        PdgNode node = PdgNode.builder().id(NODE_ID.getAndIncrement()).build();
        assignNodeData(node, baseName);
        return node;
    }

    private void assignNodeData(PdgNode node, String baseName) {
        node.getLabels().add(baseName.toUpperCase());
        node.getLabels().add(baseName.toUpperCase()+"_EXTRA");
        Map<String,Object> props = new HashMap<>();
        props.put("author", "carlo_pdg");
        props.put("version", "3.0");
        props.put("info", baseName + "_pdg_node_info");
        node.setProperties(props);
    }

    private PdgEdge createEdge(PdgNode from, PdgNode to, String relType) {
        PdgEdge edge = PdgEdge.builder().from(from).to(to).build();
        assignEdgeData(edge, relType);
        return edge;
    }

    private void assignEdgeData(PdgEdge edge, String relType) {
        edge.getLabels().add("REL_LABEL");
        edge.getLabels().add("REL_LABEL_EXTRA");
        Map<String,Object> props = new HashMap<>();
        props.put("rel_type", relType);
        props.put("weight", 10);
        props.put("info","pdg edge info");
        props.put("extra","edge_extra");
        edge.setProperties(props);
    }

    public PdgGraph buildPDG(CfgGraph cfg) {
        PdgGraph pdg = PdgGraph.builder().build();

        Map<CfgNode,PdgNode> nodeMap = new HashMap<>();
        for (CfgNode cn : cfg.getAllNodes()) {
            String baseName = cn.getLabels().isEmpty() ? "UNKNOWN" : cn.getLabels().get(0);
            PdgNode pn = createNode(baseName);
            pdg.getNodes().add(pn);
            nodeMap.put(cn, pn);
        }

        for (CfgNode from : cfg.getAllNodes()) {
            PdgNode fromPdg = nodeMap.get(from);
            for (CfgNode to : from.getSuccessors()) {
                PdgNode toPdg = nodeMap.get(to);
                PdgEdge edge = createEdge(fromPdg, toPdg, "CONTROL");
                pdg.getEdges().add(edge);
            }
        }

        return pdg;
    }
}
