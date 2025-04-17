package com.qcpg.qcpg.service.graphAnalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcpg.qcpg.dto.EntanglementPatternDTO;
import com.qcpg.qcpg.dto.graphAnalysis.*;
import com.qcpg.qcpg.repository.graphAnalysis.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class Neo4jService {

    @Autowired
    private GraphAnalysisRepository nodeRepository;

    private static final String PYTHON_INTERPRETER = "python";

    private static class OpData {
        Long id;
        Set<Long> qubits;
        List<Long> successors = new ArrayList<>();
        List<Long> predecessors = new ArrayList<>();
        int inDegree = 0;
        int layer = 0;

        List<String> labels;

        OpData(Long id, Set<Long> qubits) {
            this.id = id;
            this.qubits = qubits;
        }
    }

    public List<MetricsByFileDTO> getAllMetrics() {
        List<String> files = nodeRepository.getDistinctFiles();
        List<MetricsByFileDTO> result = new ArrayList<>();
        for (String f : files) {
            if (f == null)
                continue;

            MetricsByFileDTO dto = new MetricsByFileDTO();
            dto.setWidth(nodeRepository.getWidth(f));
            dto.setDepth(nodeRepository.getDepth(f));

            List<OperationProjection> opsRaw = nodeRepository.getOperations(f);
            List<ExecEdgeDTOEx> edgesRaw = nodeRepository.getExecOrderEdges(f);

            Map<Long, OpData> opMap = new HashMap<>();
            for (OperationProjection op : opsRaw) {
                Long opId = op.getOpId();
                List<Long> qbList = op.getQubitIds();
                Set<Long> qbSet = (qbList != null) ? new HashSet<>(qbList) : new HashSet<>();

                OpData data = new OpData(opId, qbSet);
                data.labels = op.getLabels();
                opMap.put(opId, data);
            }

            for (ExecEdgeDTOEx edge : edgesRaw) {
                Long sId = edge.getSourceId();
                Long tId = edge.getTargetId();
                if (opMap.containsKey(sId) && opMap.containsKey(tId)) {
                    OpData source = opMap.get(sId);
                    OpData target = opMap.get(tId);
                    source.successors.add(tId);
                    target.inDegree++;
                    target.predecessors.add(sId);
                }
            }

            Map<Long, Set<Long>> sameQubitMap = new HashMap<>();
            List<OpData> allOps = new ArrayList<>(opMap.values());
            for (OpData od : allOps) {
                sameQubitMap.put(od.id, new HashSet<>());
            }
            for (int i = 0; i < allOps.size(); i++) {
                for (int j = i + 1; j < allOps.size(); j++) {
                    OpData a = allOps.get(i);
                    OpData b = allOps.get(j);
                    Set<Long> inter = new HashSet<>(a.qubits);
                    inter.retainAll(b.qubits);
                    if (!inter.isEmpty()) {
                        sameQubitMap.get(a.id).add(b.id);
                        sameQubitMap.get(b.id).add(a.id);
                    }
                }
            }

            List<Long> topoOrder = new ArrayList<>();
            Map<Long, Integer> inDegreeCopy = new HashMap<>();
            for (OpData op : opMap.values()) {
                inDegreeCopy.put(op.id, op.inDegree);
            }
            Queue<Long> zeroIn = new LinkedList<>();
            for (OpData op : opMap.values()) {
                if (inDegreeCopy.get(op.id) == 0) {
                    zeroIn.add(op.id);
                }
            }
            while (!zeroIn.isEmpty()) {
                Long currentId = zeroIn.poll();
                topoOrder.add(currentId);
                OpData currentOp = opMap.get(currentId);
                for (Long succId : currentOp.successors) {
                    inDegreeCopy.put(succId, inDegreeCopy.get(succId) - 1);
                    if (inDegreeCopy.get(succId) == 0) {
                        zeroIn.add(succId);
                    }
                }
            }

            Map<Long, Integer> indexInTopo = new HashMap<>();
            for (int i = 0; i < topoOrder.size(); i++) {
                indexInTopo.put(topoOrder.get(i), i);
            }

            for (Long opId : topoOrder) {
                OpData op = opMap.get(opId);

                int layerDep = 0;
                for (Long pId : op.predecessors) {
                    OpData pred = opMap.get(pId);
                    Set<Long> inter = new HashSet<>(pred.qubits);
                    inter.retainAll(op.qubits);
                    if (!inter.isEmpty() || isMeasureOrBarrier(pred)) {
                        layerDep = Math.max(layerDep, pred.layer);
                    }
                }
                layerDep = (layerDep == 0) ? 1 : layerDep + 1;

                int layerQub = 0;
                for (Long x : sameQubitMap.get(opId)) {
                    Integer idxX = indexInTopo.get(x);
                    Integer idxOp = indexInTopo.get(opId);

                    if (idxX != null && idxOp != null && idxX < idxOp) {
                        layerQub = Math.max(layerQub, opMap.get(x).layer);
                    }
                }
                layerQub = (layerQub == 0) ? 1 : layerQub + 1;

                op.layer = Math.max(layerDep, layerQub);
            }

            Map<Integer, List<OpData>> layers = new HashMap<>();
            for (Long opId : topoOrder) {
                OpData op = opMap.get(opId);
                int candidate = op.layer;

                while (layers.containsKey(candidate) && layers.get(candidate).size() >= dto.getWidth()) {
                    candidate++;
                }
                op.layer = candidate;
                layers.computeIfAbsent(candidate, k -> new ArrayList<>()).add(op);
            }

            int maxDens = layers.values().stream().mapToInt(List::size).max().orElse(0);
            double avgDens = layers.values().stream().mapToInt(List::size).average().orElse(0.0);

            dto.setNumQubits(nodeRepository.getNumQubits(f));
            dto.setNumClassicalBits(nodeRepository.getNumClassicalBits(f));

            dto.setMaxDens((long) maxDens);
            dto.setAvgDens(avgDens);

            dto.setNoPx(nodeRepository.getNoPx(f));
            dto.setNoPy(nodeRepository.getNoPy(f));
            dto.setNoPz(nodeRepository.getNoPz(f));
            dto.setTNoP(nodeRepository.getTNoP(f));
            dto.setNoH(nodeRepository.getNoH(f));
            dto.setPSposQ(getPSposQ(f, "QUANTUM_GATE_H"));
            dto.setNoOtherSG(nodeRepository.getNoOtherSG(f));
            dto.setTNoSG(nodeRepository.getTNoSG(f));
            dto.setTNoCSQG(nodeRepository.getTNoCSQG(f));

            dto.setNoSWAP(nodeRepository.getNoSWAP(f));
            dto.setNoCNOT(nodeRepository.getNoCNOT(f));
            dto.setPQInCNOT(nodeRepository.getPQInCNOT(f));
            dto.setMaxCNOT(nodeRepository.getMaxCNOT(f));
            dto.setNoToff(nodeRepository.getNoToff(f));
            dto.setPQInToff(nodeRepository.getPQInToff(f));
            dto.setMaxToff(nodeRepository.getMaxToff(f));

            dto.setNoGates(nodeRepository.getNoGates(f));
            dto.setNoCGates(nodeRepository.getNoCGates(f));
            dto.setPSGates(nodeRepository.getPSGates(f));

            dto.setNoOr(nodeRepository.getNoOr(f));
            dto.setPQInOr(nodeRepository.getPQInOr(f));

            dto.setNoM(nodeRepository.getNoM(f));
            dto.setPQM(nodeRepository.getPQM(f));

            dto.setFile(f);
            result.add(dto);
        }
        return result;
    }

    private boolean isMeasureOrBarrier(OpData op) {
        if (op.labels == null)
            return false;
        return op.labels.contains("QUANTUM_MEASURE")
                || op.labels.contains("QUANTUM_GATE_BARRIER");
    }

    public List<PatternsByFileDTO> getAllPatterns() {
        List<String> files = nodeRepository.getDistinctFiles();
        List<PatternsByFileDTO> result = new ArrayList<>();
        for (String f : files) {
            if (f == null)
                continue;

            PatternsByFileDTO dto = new PatternsByFileDTO();

            double spx = getPSposQ(f, "QUANTUM_GATE_X");
            double spy = getPSposQ(f, "QUANTUM_GATE_Y");
            double spz = getPSposQ(f, "QUANTUM_GATE_Z");
            boolean isx = (Math.abs(spx - 100.0) < 0.000001);
            boolean isy = (Math.abs(spy - 100.0) < 0.000001);
            boolean isz = (Math.abs(spz - 100.0) < 0.000001);
            String spGate = "";
            if (isx) {
                spGate = "x";
            } else if (isy) {
                spGate = "y";
            } else if (isz) {
                spGate = "z";
            }
            Map<Boolean, String> stPrepResult = Map.of(isx || isy || isz, spGate);
            dto.setStatePreparation(stPrepResult);

            double spos = getPSposQ(f, "QUANTUM_GATE_H");
            boolean isUniform = (Math.abs(spos - 100.0) < 0.000001);
            dto.setUniformSuperposition(isUniform);

            List<Map<Integer, String>> entList = detectCreatingEntanglement(f);
            dto.setCreatingEntanglement(entList);

            List<String> knittingSubcircuits = detectKnittingSubcircuits(f);
            dto.setKnittingSubcircuits(knittingSubcircuits);

            dto.setFile(f);

            result.add(dto);
        }
        return result;
    }

    private List<String> detectKnittingSubcircuits(String file) {
        List<OperationProjection> opsRaw = nodeRepository.getOperationsForKnitting(file);
        List<PDGEdgeDTO> pdgEdges = nodeRepository.getPDGEdges(file);

        Map<Long, OpData> opMap = new HashMap<>();
        for (OperationProjection op : opsRaw) {
            Long opId = op.getOpId();
            List<Long> qbList = op.getQubitIds();
            Set<Long> qbSet = (qbList != null) ? new HashSet<>(qbList) : new HashSet<>();
            opMap.put(opId, new OpData(opId, qbSet));
        }

        Map<Long, Set<Long>> connectivityGraph = new HashMap<>();
        for (Long opId : opMap.keySet()) {
            connectivityGraph.put(opId, new HashSet<>());
        }
        List<Long> opIds = new ArrayList<>(opMap.keySet());
        for (int i = 0; i < opIds.size(); i++) {
            for (int j = i + 1; j < opIds.size(); j++) {
                Long aId = opIds.get(i);
                Long bId = opIds.get(j);
                Set<Long> qa = opMap.get(aId).qubits;
                Set<Long> qb = opMap.get(bId).qubits;
                Set<Long> inter = new HashSet<>(qa);
                inter.retainAll(qb);
                if (!inter.isEmpty()) {
                    connectivityGraph.get(aId).add(bId);
                    connectivityGraph.get(bId).add(aId);
                }
            }
        }

        for (PDGEdgeDTO edge : pdgEdges) {
            Long src = edge.getSourceId();
            Long tgt = edge.getTargetId();
            if (opMap.containsKey(src) && opMap.containsKey(tgt)) {
                connectivityGraph.get(src).add(tgt);
                connectivityGraph.get(tgt).add(src);
            }
        }

        Set<Long> visited = new HashSet<>();
        List<Set<Long>> components = new ArrayList<>();
        for (Long opId : connectivityGraph.keySet()) {
            if (!visited.contains(opId)) {
                Set<Long> comp = new HashSet<>();
                dfsComponent(opId, connectivityGraph, visited, comp);
                components.add(comp);
            }
        }

        Map<Long, OperationProjection> opProjectionMap = new HashMap<>();
        for (OperationProjection op : opsRaw) {
            opProjectionMap.put(op.getOpId(), op);
        }
        List<String> subcircuits = new ArrayList<>();
        int compIndex = 0;
        for (Set<Long> comp : components) {
            List<Long> compList = new ArrayList<>(comp);
            Collections.sort(compList);
            List<String> repList = new ArrayList<>();
            for (Long opId : compList) {
                OperationProjection opProj = opProjectionMap.get(opId);
                if (opProj != null) {
                    if (opProj.getNormalizedCodeLine() != null && !opProj.getNormalizedCodeLine().isEmpty()) {
                        repList.add(opProj.getNormalizedCodeLine());
                    } else if (opProj.getLineOfCode() != null) {
                        repList.add("Line " + opProj.getLineOfCode());
                    } else {
                        repList.add("Node " + opId);
                    }
                } else {
                    repList.add("Node " + opId);
                }
            }
            String rep = "Subcircuit " + compIndex + ": " + repList.toString();
            subcircuits.add(rep);
            compIndex++;
        }
        return subcircuits;
    }

    private void dfsComponent(Long node, Map<Long, Set<Long>> graph, Set<Long> visited, Set<Long> component) {
        visited.add(node);
        component.add(node);
        for (Long neighbor : graph.getOrDefault(node, Collections.emptySet())) {
            if (!visited.contains(neighbor)) {
                dfsComponent(neighbor, graph, visited, component);
            }
        }
    }

    private Double getPSposQ(String file, String gate) {
        List<QubitNodeDTO> qubits = nodeRepository.getQubitsForFile(file);

        List<OpNodeDTO> ops = nodeRepository.getOpsWithLabelsAndQubits(file);

        List<ExecEdgeDTO> edges = nodeRepository.getExecEdges(file);

        Map<Long, Set<Long>> adjacency = new HashMap<>();
        Map<Long, Integer> inDegree = new HashMap<>();

        for (OpNodeDTO op : ops) {
            adjacency.put(op.getOpId(), new HashSet<>());
            inDegree.put(op.getOpId(), 0);
        }

        for (ExecEdgeDTO e : edges) {
            Long s = e.getSourceId();
            Long t = e.getTargetId();
            if (adjacency.containsKey(s) && adjacency.containsKey(t)) {
                adjacency.get(s).add(t);
                inDegree.put(t, inDegree.get(t) + 1);
            }
        }

        Queue<Long> zeroInDegQueue = new LinkedList<>();
        for (Long nodeId : inDegree.keySet()) {
            if (inDegree.get(nodeId) == 0) {
                zeroInDegQueue.add(nodeId);
            }
        }

        List<Long> topoOrder = new ArrayList<>();
        while (!zeroInDegQueue.isEmpty()) {
            Long current = zeroInDegQueue.poll();
            topoOrder.add(current);
            for (Long succ : adjacency.get(current)) {
                int deg = inDegree.get(succ) - 1;
                inDegree.put(succ, deg);
                if (deg == 0) {
                    zeroInDegQueue.add(succ);
                }
            }
        }

        Map<Long, OpNodeDTO> opMap = new HashMap<>();
        for (OpNodeDTO op : ops) {
            opMap.put(op.getOpId(), op);
        }

        Map<Long, Integer> levelMap = new HashMap<>();
        for (int i = 0; i < topoOrder.size(); i++) {
            levelMap.put(topoOrder.get(i), i);
        }

        int totalQ = qubits.size();
        int hCount = 0;

        for (QubitNodeDTO qb : qubits) {
            Long firstOpId = null;
            int minLevel = Integer.MAX_VALUE;

            for (OpNodeDTO op : ops) {
                if (op.getQubitIds() != null && op.getQubitIds().contains(qb.getId())) {
                    int lvl = levelMap.getOrDefault(op.getOpId(), Integer.MAX_VALUE);
                    if (lvl < minLevel) {
                        minLevel = lvl;
                        firstOpId = op.getOpId();
                    }
                }
            }

            if (firstOpId != null) {
                OpNodeDTO earliestOp = opMap.get(firstOpId);
                if (earliestOp.getLabels() != null
                        && earliestOp.getLabels().contains(gate)) {
                    hCount++;
                }
            }
        }

        if (totalQ == 0)
            return 0.0;
        return (hCount * 100.0) / totalQ;
    }

    private List<Map<Integer, String>> detectCreatingEntanglement(String file) {
        List<EntanglementPatternDTO> patterns = nodeRepository.getCreatingEntanglementPatterns(file);

        List<Map<Integer, String>> result = new ArrayList<>();
        int idx = 1;

        for (EntanglementPatternDTO p : patterns) {
            String controlName = (p.getQubitControl() != null) ? p.getQubitControl().getName() : "";
            String targetName = (p.getQubitTarget() != null) ? p.getQubitTarget().getName() : "";

            String qubitsString = controlName + "," + targetName;

            result.add(Map.of(idx, qubitsString));
            idx++;
        }

        return result;
    }

    public MultipartFile generateDashboardExcel(List<?> dataList) throws Exception {
        File tempJson = File.createTempFile("dashboard_data_", ".json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(tempJson, dataList);

        ClassPathResource cpr = new ClassPathResource("pythonCode/create_excel_dashboard.py");
        File scriptFile = cpr.getFile();

        File outXlsx = File.createTempFile("dashboard_out_", ".xlsx");

        ProcessBuilder pb = new ProcessBuilder(
                PYTHON_INTERPRETER,
                scriptFile.getAbsolutePath(),
                tempJson.getAbsolutePath(),
                outXlsx.getAbsolutePath());
        pb.redirectErrorStream(true);

        Process process = pb.start();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("PYTHON-OUTPUT: " + line);
            }
        }
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Python script exited with code " + exitCode);
        }

        try (FileInputStream fis = new FileInputStream(outXlsx)) {
            byte[] bytes = fis.readAllBytes();
            String fileName = "dashboard.xlsx";

            return new MockMultipartFile(
                    "file",
                    fileName,
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    bytes);
        } finally {
            tempJson.delete();
            outXlsx.delete();
        }
    }
}