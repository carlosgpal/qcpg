package com.qcpg.qcpg.controller.graphAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.qcpg.qcpg.dto.graphAnalysis.MetricsByFileDTO;
import com.qcpg.qcpg.dto.graphAnalysis.PatternsByFileDTO;
import com.qcpg.qcpg.service.graphAnalysis.*;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/analyze")
public class Neo4jController {

    @Autowired
    private Neo4jService neo4jService;

    @GetMapping("allMetrics")
    public ResponseEntity<List<MetricsByFileDTO>> getAllMetrics() {
        return ResponseEntity.ok(neo4jService.getAllMetrics());
    }

    @GetMapping("allPatterns")
    public ResponseEntity<List<PatternsByFileDTO>> getAllPatterns() {
        return ResponseEntity.ok(neo4jService.getAllPatterns());
    }

    @GetMapping("/download")
    public void downloadAnalysisExcel(HttpServletResponse response) {
        try {
            List<MetricsByFileDTO> allMetrics = neo4jService.getAllMetrics();
            List<PatternsByFileDTO> allPatterns = neo4jService.getAllPatterns();

            List<Map<String, Object>> combinedData = combineMetricsAndPatterns(allMetrics, allPatterns);

            MultipartFile excelFile = neo4jService.generateDashboardExcel(combinedData);

            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"analysis_dashboard.zip\"");

            response.setStatus(HttpStatus.OK.value());

            response.getOutputStream().write(excelFile.getBytes());
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private List<Map<String, Object>> combineMetricsAndPatterns(
            List<MetricsByFileDTO> allMetrics,
            List<PatternsByFileDTO> allPatterns) {
        Map<String, PatternsByFileDTO> patternsMap = new HashMap<>();
        for (PatternsByFileDTO p : allPatterns) {
            patternsMap.put(p.getFile(), p);
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (MetricsByFileDTO m : allMetrics) {
            Map<String, Object> item = new HashMap<>();
            item.put("file", m.getFile());
            item.put("width", m.getWidth());
            item.put("depth", m.getDepth());
            item.put("noGates", m.getNoGates());
            item.put("noCNOT", m.getNoCNOT());
            item.put("noToff", m.getNoToff());
            item.put("noSWAP", m.getNoSWAP());
            item.put("maxDens", m.getMaxDens());
            item.put("avgDens", m.getAvgDens());
            item.put("noPx", m.getNoPx());
            item.put("noPy", m.getNoPy());
            item.put("noPz", m.getNoPz());
            item.put("noH", m.getNoH());
            item.put("noOtherSG", m.getNoOtherSG());
            item.put("maxCNOT", m.getMaxCNOT());
            item.put("maxToff", m.getMaxToff());
            item.put("noCGates", m.getNoCGates());
            item.put("noOr", m.getNoOr());
            item.put("noM", m.getNoM());
            item.put("tnoSG", m.getTNoSG());
            item.put("tnoCSQG", m.getTNoCSQG());
            item.put("psposQ", m.getPSposQ());
            item.put("pqinCNOT", m.getPQInCNOT());
            item.put("tnoP", m.getTNoP());
            item.put("pqm", m.getPQM());
            item.put("pqinToff", m.getPQInToff());
            item.put("pqinOr", m.getPQInOr());
            item.put("psgates", m.getPSGates());

            PatternsByFileDTO pat = patternsMap.get(m.getFile());
            if (pat != null) {
                Map<String, Object> patternsSubmap = new HashMap<>();
                if (pat.getStatePreparation() != null && !pat.getStatePreparation().isEmpty()) {
                    Boolean key = pat.getStatePreparation().keySet().iterator().next();
                    String val = pat.getStatePreparation().get(key);
                    patternsSubmap.put("statePrep", key + "->" + val);
                } else {
                    patternsSubmap.put("statePrep", "None");
                }
                patternsSubmap.put("uniform", pat.isUniformSuperposition());
                if (pat.getCreatingEntanglement() != null && !pat.getCreatingEntanglement().isEmpty()) {
                    List<String> entStrings = new ArrayList<>();
                    for (Map<Integer, String> entItem : pat.getCreatingEntanglement()) {
                        for (Map.Entry<Integer, String> e : entItem.entrySet()) {
                            entStrings.add("Ent#" + e.getKey() + ": " + e.getValue());
                        }
                    }
                    patternsSubmap.put("entanglement", entStrings);
                } else {
                    patternsSubmap.put("entanglement", new ArrayList<>());
                }
                if (pat.getKnittingSubcircuits() != null && !pat.getKnittingSubcircuits().isEmpty()) {
                    patternsSubmap.put("knittingSubcircuits", pat.getKnittingSubcircuits());
                } else {
                    patternsSubmap.put("knittingSubcircuits", new ArrayList<>());
                }
                item.put("patterns", patternsSubmap);
            }
            result.add(item);
        }
        return result;
    }
}
