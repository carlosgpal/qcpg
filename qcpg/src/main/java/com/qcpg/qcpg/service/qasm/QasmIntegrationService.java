package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.model.graphCreation.*;
import com.qcpg.qcpg.repository.graphCreation.GraphCreationRepository;
import com.qcpg.qcpg.service.ExportService;
import com.qcpg.qcpg.service.Neo4jIntegrationService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Service for integrating QASM files into a Code Property Graph (CPG).
 * Handles the parsing, AST construction, CFG/PDG generation, and integration
 * with Neo4j.
 */
@Service
public class QasmIntegrationService {

    private final QasmParsingService parsingService;
    private final ExportService exportService;
    private final QasmCfgPdgBuilder cpgPdgBuilder;
    private final QasmQuantumGraphBuilder quantumBuilder;
    private final GraphCreationRepository graphCreationRepository;

    private static long globalIdCounter = 1; // Global counter for unique node IDs.

    /**
     * Constructor for QasmIntegrationService.
     *
     * @param parsingService          The service for parsing QASM code.
     * @param exportService           The service for exporting graph data.
     * @param cpgPdgBuilder           The builder for CFG and PDG graphs.
     * @param quantumBuilder          The builder for quantum-specific graph
     *                                components.
     * @param graphCreationRepository The repository for interacting with Neo4j.
     */
    public QasmIntegrationService(QasmParsingService parsingService,
            ExportService exportService,
            Neo4jIntegrationService neo4jIntegrationService,
            QasmCfgPdgBuilder cpgPdgBuilder,
            QasmQuantumGraphBuilder quantumBuilder,
            GraphCreationRepository graphCreationRepository) {
        this.parsingService = parsingService;
        this.exportService = exportService;
        this.cpgPdgBuilder = cpgPdgBuilder;
        this.quantumBuilder = quantumBuilder;
        this.graphCreationRepository = graphCreationRepository;
    }

    /**
     * Processes a single QASM file, generating a CPG and integrating it with Neo4j.
     *
     * @param qasmCode The QASM code as a string.
     * @param filename The name of the QASM file.
     * @throws Exception If the file cannot be processed.
     */
    public void processSingleQasmFile(String qasmCode, String filename) throws Exception {
        try {
            // Parse the QASM code and construct the AST.
            var programCtx = parsingService.parseContent(qasmCode);
            List<String> codeLines = Arrays.asList(qasmCode.split("\n"));
            QasmAstBuilder astBuilder = new QasmAstBuilder();
            AstGraph ast = astBuilder.build(programCtx, filename, codeLines);

            // Collect nodes and edges from the AST.
            List<NodeBase> allNodes = new ArrayList<>(ast.getAllNodes());
            List<EdgeBase> allEdges = new ArrayList<>();

            // Add edges for the AST hierarchy.
            for (AstNode n : ast.getAllNodes()) {
                for (AstNode c : n.getChildren()) {
                    Map<String, Object> edgeProps = Map.of("rel_type", "AST", "info", "ast_hierarchy");
                    AstEdge edge = AstEdge.builder().from(n).to(c).properties(edgeProps).build();
                    allEdges.add(edge);
                }
            }

            // Build CFG and PDG edges and include their nodes.
            List<EdgeBase> cfgEdges = cpgPdgBuilder.buildCfgEdges(ast.getRoot(), filename);
            Set<NodeBase> cfgNodes = new HashSet<>();
            for (EdgeBase e : cfgEdges) {
                cfgNodes.add(e.getFrom());
                cfgNodes.add(e.getTo());
            }
            for (NodeBase n : cfgNodes) {
                if (!allNodes.contains(n)) {
                    allNodes.add(n);
                }
            }
            allEdges.addAll(cfgEdges);

            List<AstNode> allAstNodes = ast.getAllNodes();
            List<EdgeBase> pdgEdges = cpgPdgBuilder.buildPdgEdges(allAstNodes);
            Set<NodeBase> pdgNodes = new HashSet<>();
            for (EdgeBase e : pdgEdges) {
                pdgNodes.add(e.getFrom());
                pdgNodes.add(e.getTo());
            }
            for (NodeBase n : pdgNodes) {
                if (!allNodes.contains(n)) {
                    allNodes.add(n);
                }
            }
            allEdges.addAll(pdgEdges);

            // Add quantum-specific nodes and edges.
            QuantumGraph qResult = quantumBuilder.buildQuantumGraph(codeLines, ast.getAllNodes());
            allNodes.addAll(qResult.getQuantumNodes());
            allEdges.addAll(qResult.getQuantumEdges());

            // Assign unique IDs to all nodes.
            long startId = globalIdCounter;
            for (NodeBase node : allNodes) {
                node.setId(startId++);
            }
            globalIdCounter = startId;

            // Prepare files for Neo4j integration.
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"));
            String safeFilename = filename.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
            String folderName = "graph_" + timestamp + "_" + safeFilename;

            String baseDir = "./src/main/resources/volumes/neo4j/import/";
            File dir = new File(baseDir + folderName);
            if (!dir.exists())
                dir.mkdirs();

            String nodesFile = baseDir + folderName + "/nodes.csv";
            String edgesFile = baseDir + folderName + "/edges.csv";

            String qasmFilePath = baseDir + folderName + "/" + safeFilename;
            try (FileWriter fw = new FileWriter(qasmFilePath)) {
                fw.write(qasmCode);
            }

            String folderPath = "file:///" + folderName + "/";
            String nodesFilePath = folderPath + "nodes.csv";
            String edgesFilePath = folderPath + "edges.csv";

            // Export nodes and edges to CSV.
            exportService.exportCpgToCsv(allNodes, allEdges, nodesFile, edgesFile);

            // Clear the Neo4j database and load the new graph data.
            graphCreationRepository.clearDB();
            graphCreationRepository.insertNodesFromCsv(nodesFilePath);
            graphCreationRepository.insertEdgesFromCsv(edgesFilePath);

        } catch (Exception e) {
            System.err.println("Error processing file: " + filename);
            e.printStackTrace();
            throw new Exception("Could not process the file: " + filename + " - " + e.getMessage(), e);
        }
    }

    /**
     * Processes multiple QASM files in a batch operation.
     *
     * @param files An array of QASM files to process.
     * @throws Exception If any file cannot be processed.
     */
    public void processMultipleQasmFiles(MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String qasmContent = new String(file.getBytes());
                processSingleQasmFile(qasmContent, file.getOriginalFilename());
            }
        }
    }
}
