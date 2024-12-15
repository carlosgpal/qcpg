package com.qcpg.qcpg.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qcpg.qcpg.graph.*;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExportService {

    private static final Gson GSON = new GsonBuilder().create();

    private String mapToJsonField(Map<String,Object> map) {
        String json = GSON.toJson(map);
        json = json.replace("\\\"", "\"");
        json = json.replace("\"", "\"\"");
        return "\"" + json + "\"";
    }
    
    private String listToJsonField(List<String> list) {
        String json = GSON.toJson(list);
        json = json.replace("\\\"", "\"");
        json = json.replace("\"", "\"\"");
        return "\"" + json + "\"";
    }

    public void exportAstToCsv(AstGraph ast, String nodesFile, String edgesFile) throws IOException {
        try (FileWriter nout = new FileWriter(nodesFile);
             FileWriter eout = new FileWriter(edgesFile)) {

            nout.write("id,labels,properties\n");
            eout.write("start_id,end_id,properties\n");

            List<AstNode> nodes = ast.getAllNodes();
            for (AstNode n : nodes) {

                List<String> labels = n.getLabels();
                Map<String,Object> props = n.getProperties();

                String labelsField = listToJsonField(labels);
                String propsField = mapToJsonField(props);

                nout.write(n.getId() + "," + labelsField + "," + propsField + "\n");

                Map<String,Object> edgeProps = Map.of(
                        "rel_type","CHILD",
                        "weight",10,
                        "info","some edge info",
                        "extra","edge_extra"
                );
                String edgePropsField = mapToJsonField(edgeProps);
                for (AstNode c : n.getChildren()) {
                    eout.write(n.getId() + "," + c.getId() + "," + edgePropsField + "\n");
                }
            }
        }
    }

    public void exportCfgToCsv(CfgGraph cfg, String nodesFile, String edgesFile) throws IOException {
        try (FileWriter nout = new FileWriter(nodesFile);
             FileWriter eout = new FileWriter(edgesFile)) {

            nout.write("id,labels,properties\n");
            eout.write("start_id,end_id,properties\n");

            for (CfgNode n : cfg.getAllNodes()) {
                List<String> labels = n.getLabels();
                Map<String,Object> props = n.getProperties();

                String labelsField = listToJsonField(labels);
                String propsField = mapToJsonField(props);

                nout.write(n.getId() + "," + labelsField + "," + propsField + "\n");

                Map<String,Object> edgeProps = Map.of(
                        "rel_type","FLOWS_TO",
                        "weight",5,
                        "info","cfg edge info",
                        "extra","edge_extra"
                );
                String edgePropsField = mapToJsonField(edgeProps);

                for (CfgNode succ : n.getSuccessors()) {
                    eout.write(n.getId() + "," + succ.getId() + "," + edgePropsField + "\n");
                }
            }
        }
    }

    public void exportPdgToCsv(PdgGraph pdg, String nodesFile, String edgesFile) throws IOException {
        try (FileWriter nout = new FileWriter(nodesFile);
             FileWriter eout = new FileWriter(edgesFile)) {
            nout.write("id,labels,properties\n");
            eout.write("start_id,end_id,properties\n");

            for (PdgNode pn : pdg.getNodes()) {
                List<String> labels = pn.getLabels();
                Map<String,Object> props = pn.getProperties();

                String labelsField = listToJsonField(labels);
                String propsField = mapToJsonField(props);

                nout.write(pn.getId() + "," + labelsField + "," + propsField + "\n");
            }

            for (PdgEdge edge : pdg.getEdges()) {
                Map<String,Object> eprops = edge.getProperties();
                String edgePropsField = mapToJsonField(eprops);
                eout.write(edge.getFrom().getId() + "," + edge.getTo().getId() + "," + edgePropsField + "\n");
            }
        }
    }
}
