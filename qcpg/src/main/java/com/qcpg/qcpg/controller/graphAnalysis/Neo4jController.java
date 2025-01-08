package com.qcpg.qcpg.controller.graphAnalysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qcpg.qcpg.dto.graphAnalysis.MetricsByFileDTO;
import com.qcpg.qcpg.dto.graphAnalysis.PatternsByFileDTO;
import com.qcpg.qcpg.service.graphAnalysis.*;

@RestController
@RequestMapping("/neo4j")
public class Neo4jController {

    @Autowired
    private Neo4jService neo4jService;

    @GetMapping("/entireGraph")
    public ResponseEntity<?> getEntireGraph() {
        return ResponseEntity.ok(neo4jService.getGraph());
    }

    @GetMapping("/ast")
    public ResponseEntity<?> getAstGraph() {
        return ResponseEntity.ok(neo4jService.getAst());
    }

    @GetMapping("/cfg")
    public ResponseEntity<?> getCfgGraph() {
        return ResponseEntity.ok(neo4jService.getCfg());
    }

    @GetMapping("/pdg")
    public ResponseEntity<?> getPdgGraph() {
        return ResponseEntity.ok(neo4jService.getPdg());
    }

    @GetMapping("allMetrics")
    public ResponseEntity<List<MetricsByFileDTO>> getAllMetrics() {
        return ResponseEntity.ok(neo4jService.getAllMetrics());
    }

    @GetMapping("allPatterns")
    public ResponseEntity<List<PatternsByFileDTO>> getAllPatterns() {
        return ResponseEntity.ok(neo4jService.getAllPatterns());
    }
}
