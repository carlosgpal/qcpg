package com.qcpg.qcpg.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qcpg.qcpg.model.graphCreation.*;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Service for exporting the Code Property Graph (CPG) to CSV files.
 * Converts nodes and edges into CSV format suitable for Neo4j import.
 */
@Service
public class ExportService {

    private static final Gson GSON = new GsonBuilder().create(); // Gson instance for JSON conversion.

    /**
     * Converts a map to a JSON string formatted for use in CSV fields.
     *
     * @param map The map to convert.
     * @return A JSON string formatted for CSV.
     */
    private String mapToJsonField(Map<String, Object> map) {
        String json = GSON.toJson(map);
        json = json.replace("\\\"", "\"");
        json = json.replace("\"", "\"\"");
        return "\"" + json + "\"";
    }

    /**
     * Converts a list to a JSON string formatted for use in CSV fields.
     *
     * @param list The list to convert.
     * @return A JSON string formatted for CSV.
     */
    private String listToJsonField(List<String> list) {
        String json = GSON.toJson(list);
        json = json.replace("\\\"", "\"");
        json = json.replace("\"", "\"\"");
        return "\"" + json + "\"";
    }

    /**
     * Exports the nodes and edges of a CPG to separate CSV files.
     *
     * @param allNodes  The list of nodes to export.
     * @param allEdges  The list of edges to export.
     * @param nodesFile The file path for the nodes CSV.
     * @param edgesFile The file path for the edges CSV.
     * @throws IOException If an error occurs while writing to the files.
     */
    public void exportCpgToCsv(List<NodeBase> allNodes, List<EdgeBase> allEdges, String nodesFile, String edgesFile)
            throws IOException {
        try (FileWriter nout = new FileWriter(nodesFile);
                FileWriter eout = new FileWriter(edgesFile)) {
            // Write CSV headers
            nout.write("id,labels,properties\n");
            eout.write("start_id,end_id,properties\n");

            // Write node data to the nodes file
            for (NodeBase n : allNodes) {
                String labelsField = listToJsonField(n.getLabels());
                String propsField = mapToJsonField(n.getProperties());
                nout.write(n.getId() + "," + labelsField + "," + propsField + "\n");
            }

            // Write edge data to the edges file
            for (EdgeBase edge : allEdges) {
                String propsField = mapToJsonField(edge.getProperties());
                eout.write(edge.getFrom().getId() + "," + edge.getTo().getId() + "," + propsField + "\n");
            }
        }
    }
}
