package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.graph.AstGraph;
import com.qcpg.qcpg.graph.CfgGraph;
import com.qcpg.qcpg.graph.PdgGraph;
import com.qcpg.qcpg.service.ExportService;
import com.qcpg.qcpg.service.Neo4jIntegrationService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class QasmIntegrationService {

    private final QasmParsingService parsingService;
    private final ExportService exportService;
    private final Neo4jIntegrationService neo4jIntegrationService;

    public QasmIntegrationService(QasmParsingService parsingService, ExportService exportService, Neo4jIntegrationService neo4jIntegrationService) {
        this.parsingService = parsingService;
        this.exportService = exportService;
        this.neo4jIntegrationService = neo4jIntegrationService;
    }

    public void processQasm(String qasmCode, String filename) throws Exception {
        var programCtx = parsingService.parseContent(qasmCode);

        List<String> codeLines = Arrays.asList(qasmCode.split("\n"));

        QasmAstBuilder astBuilder = new QasmAstBuilder();
        AstGraph ast = astBuilder.build(programCtx, filename, codeLines);

        QasmCfgBuilder cfgBuilder = new QasmCfgBuilder();
        CfgGraph cfg = cfgBuilder.buildCFG(ast.getRoot());

        QasmPdgBuilder pdgBuilder = new QasmPdgBuilder();
        PdgGraph pdg = pdgBuilder.buildPDG(cfg);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"));
        String folderName = "graph_" + timestamp;

        String baseDir = "./src/main/resources/volumes/neo4j/import/";
        File dir = new File(baseDir + folderName);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String astNodes = baseDir + folderName + "/ast_nodes.csv";
        String astEdges = baseDir + folderName + "/ast_edges.csv";
        exportService.exportAstToCsv(ast, astNodes, astEdges);

        String cfgNodes = baseDir + folderName + "/cfg_nodes.csv";
        String cfgEdges = baseDir + folderName + "/cfg_edges.csv";
        exportService.exportCfgToCsv(cfg, cfgNodes, cfgEdges);

        String pdgNodes = baseDir + folderName + "/pdg_nodes.csv";
        String pdgEdges = baseDir + folderName + "/pdg_edges.csv";
        exportService.exportPdgToCsv(pdg, pdgNodes, pdgEdges);

        String cypherDelete= 
            "MATCH (n) DETACH DELETE n;";
        String cypherCreate = 
            "CALL {\n" +
            "  LOAD CSV WITH HEADERS FROM 'file:///" + folderName + "/ast_nodes.csv' AS line\n" +
            "  WITH line, apoc.convert.fromJsonList(line.labels) AS labList, apoc.convert.fromJsonMap(line.properties) AS props\n" +
            "  CREATE (n)\n" +
            "  SET n.id = toInteger(line.id)\n" +
            "  SET n += props\n" +
            "  WITH n, labList\n" +
            "  CALL apoc.create.addLabels(n, labList) YIELD node\n" +
            "  RETURN count(*) AS astNodesCreated\n" +
            "}\n" +
            "CALL {\n" +
            "  LOAD CSV WITH HEADERS FROM 'file:///" + folderName + "/ast_edges.csv' AS line\n" +
            "  WITH line, apoc.convert.fromJsonMap(line.properties) AS eprops\n" +
            "  MATCH (start {id: toInteger(line.start_id)}), (end {id: toInteger(line.end_id)})\n" +
            "  WITH eprops, start, end\n" +
            "  CALL {\n" +
            "    WITH eprops\n" +
            "    RETURN eprops.rel_type AS relType\n" +
            "  }\n" +
            "  CALL apoc.create.relationship(start, relType, eprops, end) YIELD rel\n" +
            "  RETURN count(*) AS astRelsCreated\n" +
            "}\n" +
            "// CFG\n" +
            "CALL {\n" +
            "  LOAD CSV WITH HEADERS FROM 'file:///" + folderName + "/cfg_nodes.csv' AS line\n" +
            "  WITH line, apoc.convert.fromJsonList(line.labels) AS labList, apoc.convert.fromJsonMap(line.properties) AS props\n" +
            "  CREATE (n)\n" +
            "  SET n.id = toInteger(line.id)\n" +
            "  SET n += props\n" +
            "  WITH n, labList\n" +
            "  CALL apoc.create.addLabels(n, labList) YIELD node\n" +
            "  RETURN count(*) AS cfgNodesCreated\n" +
            "}\n" +
            "CALL {\n" +
            "  LOAD CSV WITH HEADERS FROM 'file:///" + folderName + "/cfg_edges.csv' AS line\n" +
            "  WITH line, apoc.convert.fromJsonMap(line.properties) AS eprops\n" +
            "  MATCH (start {id: toInteger(line.start_id)}), (end {id: toInteger(line.end_id)})\n" +
            "  WITH eprops, start, end\n" +
            "  CALL {\n" +
            "    WITH eprops\n" +
            "    RETURN eprops.rel_type AS relType\n" +
            "  }\n" +
            "  CALL apoc.create.relationship(start, relType, eprops, end) YIELD rel\n" +
            "  RETURN count(*) AS cfgRelsCreated\n" +
            "}\n" +
            "// PDG\n" +
            "CALL {\n" +
            "  LOAD CSV WITH HEADERS FROM 'file:///" + folderName + "/pdg_nodes.csv' AS line\n" +
            "  WITH line, apoc.convert.fromJsonList(line.labels) AS labList, apoc.convert.fromJsonMap(line.properties) AS props\n" +
            "  CREATE (n)\n" +
            "  SET n.id = toInteger(line.id)\n" +
            "  SET n += props\n" +
            "  WITH n, labList\n" +
            "  CALL apoc.create.addLabels(n, labList) YIELD node\n" +
            "  RETURN count(*) AS pdgNodesCreated\n" +
            "}\n" +
            "CALL {\n" +
            "  LOAD CSV WITH HEADERS FROM 'file:///" + folderName + "/pdg_edges.csv' AS line\n" +
            "  WITH line, apoc.convert.fromJsonMap(line.properties) AS eprops\n" +
            "  MATCH (start {id: toInteger(line.start_id)}), (end {id: toInteger(line.end_id)})\n" +
            "  WITH eprops, start, end\n" +
            "  CALL {\n" +
            "    WITH eprops\n" +
            "    RETURN eprops.rel_type AS relType\n" +
            "  }\n" +
            "  CALL apoc.create.relationship(start, relType, eprops, end) YIELD rel\n" +
            "  RETURN count(*) AS pdgRelsCreated\n" +
            "}\n" +
            "RETURN \"Import completed\" AS message;";

        neo4jIntegrationService.runCypher(cypherDelete);
        neo4jIntegrationService.runCypher(cypherCreate);
    }
}
