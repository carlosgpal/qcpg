package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.antlr4.qasm3Parser;
import com.qcpg.qcpg.model.graphCreation.*;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service for analyzing and processing multiple QASM files into a single global
 * Code Property Graph (CPG).
 * Handles AST construction, CFG and PDG generation, and node and edge
 * aggregation.
 */
@Service
public class QasmProjectAnalyzer {

    private final QasmParsingService parsingService;
    private final QasmCfgPdgBuilder cfgPdgBuilder;
    private static final AtomicLong ID_GEN = new AtomicLong(1); // Global ID generator for nodes.
    private List<AstGraph> parsedAsts = new ArrayList<>(); // List of parsed ASTs from QASM files.

    /**
     * Constructor for QasmProjectAnalyzer.
     *
     * @param parsingService Service for parsing QASM content into an AST.
     * @param cfgPdgBuilder  Builder for generating CFG and PDG from AST nodes.
     */
    public QasmProjectAnalyzer(QasmParsingService parsingService, QasmCfgPdgBuilder cfgPdgBuilder) {
        this.parsingService = parsingService;
        this.cfgPdgBuilder = cfgPdgBuilder;
    }

    /**
     * Parses and adds a QASM file to the analyzer.
     *
     * @param qasmCode The QASM code as a string.
     * @param filename The name of the file being processed.
     * @throws Exception If parsing fails or syntax errors are found.
     */
    public void addQasmFile(String qasmCode, String filename) throws Exception {
        qasm3Parser.ProgramContext programCtx = parsingService.parseContent(qasmCode);
        List<String> codeLines = Arrays.asList(qasmCode.split("\n"));
        QasmAstBuilder astBuilder = new QasmAstBuilder();
        AstGraph ast = astBuilder.build(programCtx, filename, codeLines);
        parsedAsts.add(ast); // Add the parsed AST to the list.
    }

    /**
     * Builds a global AST by combining all parsed ASTs.
     *
     * @return The global `AstGraph` representing the combined ASTs.
     */
    public AstGraph buildGlobalAst() {
        // Create a root node for the global AST.
        AstNode root = AstNode.builder().id(ID_GEN.getAndIncrement()).build();
        root.getLabels().add("AST_NODE");
        root.getProperties().put("node_type", "project");
        root.getProperties().put("info", "Global project node");
        root.getProperties().put("author", "qasm_project_analyzer");
        root.getProperties().put("version", "1.0");

        // Attach the roots of all parsed ASTs as children of the global root.
        for (AstGraph g : parsedAsts) {
            if (g.getRoot() != null) {
                root.getChildren().add(g.getRoot());
            }
        }
        return AstGraph.builder().root(root).build();
    }

    /**
     * Combines all nodes from the global AST into a single list for the CPG.
     *
     * @return A list of all nodes in the global AST.
     */
    public List<NodeBase> buildCombinedCpgNodes() {
        AstGraph globalAst = buildGlobalAst();
        return new ArrayList<>(globalAst.getAllNodes());
    }

    /**
     * Combines all edges from the global AST, CFG, and PDG into a single list for
     * the CPG.
     *
     * @param filename The name of the file being processed.
     * @return A list of all edges in the global CPG.
     */
    public List<EdgeBase> buildCombinedCpgEdges(String filename) {
        AstGraph globalAst = buildGlobalAst();
        List<EdgeBase> allEdges = new ArrayList<>();

        // Add edges for AST hierarchy.
        for (AstNode n : globalAst.getAllNodes()) {
            for (AstNode c : n.getChildren()) {
                Map<String, Object> edgeProps = Map.of("rel_type", "AST", "info", "ast_hierarchy");
                AstEdge edge = AstEdge.builder().from(n).to(c).properties(edgeProps).build();
                allEdges.add(edge);
            }
        }

        // Add CFG edges.
        List<EdgeBase> cfgEdges = cfgPdgBuilder.buildCfgEdges(globalAst.getRoot(), filename);
        allEdges.addAll(cfgEdges);

        // Add PDG edges.
        List<AstNode> allAstNodes = globalAst.getAllNodes();
        List<EdgeBase> pdgEdges = cfgPdgBuilder.buildPdgEdges(allAstNodes);
        allEdges.addAll(pdgEdges);

        return allEdges;
    }

    /**
     * Retrieves all nodes from the global CPG, including additional nodes from CFG
     * and PDG.
     *
     * @param filename The name of the file being processed.
     * @return A list of all nodes in the CPG.
     */
    public List<NodeBase> getAllNodesWithCfgPdg(String filename) {
        AstGraph globalAst = buildGlobalAst();
        List<NodeBase> allNodes = new ArrayList<>(globalAst.getAllNodes());

        // Add extra nodes from CFG and PDG edges.
        Set<NodeBase> extraNodes = new HashSet<>();
        List<EdgeBase> cfgEdges = cfgPdgBuilder.buildCfgEdges(globalAst.getRoot(), filename);
        for (EdgeBase e : cfgEdges) {
            extraNodes.add(e.getFrom());
            extraNodes.add(e.getTo());
        }

        List<AstNode> allAstNodes = globalAst.getAllNodes();
        List<EdgeBase> pdgEdges = cfgPdgBuilder.buildPdgEdges(allAstNodes);
        for (EdgeBase e : pdgEdges) {
            extraNodes.add(e.getFrom());
            extraNodes.add(e.getTo());
        }

        // Ensure all nodes are included.
        for (NodeBase n : extraNodes) {
            if (!allNodes.contains(n)) {
                allNodes.add(n);
            }
        }

        return allNodes;
    }

    /**
     * Retrieves all edges from the global CPG.
     *
     * @param filename The name of the file being processed.
     * @return A list of all edges in the CPG.
     */
    public List<EdgeBase> getAllEdges(String filename) {
        return buildCombinedCpgEdges(filename);
    }

    /**
     * Resets the analyzer by clearing all previously parsed ASTs.
     */
    public void reset() {
        parsedAsts.clear();
    }
}
