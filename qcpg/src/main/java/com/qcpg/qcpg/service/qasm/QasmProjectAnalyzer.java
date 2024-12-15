package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.antlr4.qasm3Parser;
import com.qcpg.qcpg.graph.AstGraph;
import com.qcpg.qcpg.graph.AstNode;
import com.qcpg.qcpg.graph.CfgGraph;
import com.qcpg.qcpg.graph.PdgGraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class QasmProjectAnalyzer {

    private final QasmParsingService parsingService;
    private final QasmCfgBuilder cfgBuilder;
    private final QasmPdgBuilder pdgBuilder;

    private List<AstGraph> parsedAsts = new ArrayList<>();
    private static final AtomicLong ID_GEN = new AtomicLong(1);

    public QasmProjectAnalyzer(QasmParsingService parsingService,
                               QasmCfgBuilder cfgBuilder,
                               QasmPdgBuilder pdgBuilder) {
        this.parsingService = parsingService;
        this.cfgBuilder = cfgBuilder;
        this.pdgBuilder = pdgBuilder;
    }

    public void addQasmFile(String qasmCode, String filename) throws Exception {
        qasm3Parser.ProgramContext programCtx = parsingService.parseContent(qasmCode);
        List<String> codeLines = Arrays.asList(qasmCode.split("\n"));
        QasmAstBuilder astBuilder = new QasmAstBuilder();
        AstGraph ast = astBuilder.build(programCtx, filename, codeLines);
        parsedAsts.add(ast);
    }

    public AstGraph buildGlobalAst() {
        AstNode root = AstNode.builder().id(ID_GEN.getAndIncrement()).build();
        root.getLabels().add("PROJECT");
        root.getLabels().add("PROJECT_EXTRA");
        Map<String,Object> props = new HashMap<>();
        props.put("author","carlo_proj");
        props.put("version","1.0");
        props.put("info","project_node_info");
        root.setProperties(props);

        for (AstGraph g : parsedAsts) {
            if (g.getRoot() != null) {
                root.getChildren().add(g.getRoot());
            }
        }

        AstGraph globalAst = AstGraph.builder().root(root).build();
        return globalAst;
    }

    public CfgGraph buildGlobalCfg(AstGraph globalAst) {
        return cfgBuilder.buildCFG(globalAst.getRoot());
    }

    public PdgGraph buildGlobalPdg(CfgGraph cfg) {
        return pdgBuilder.buildPDG(cfg);
    }

    public void reset() {
        parsedAsts.clear();
    }
}
