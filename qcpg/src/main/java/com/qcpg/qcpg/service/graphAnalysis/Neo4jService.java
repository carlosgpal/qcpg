package com.qcpg.qcpg.service.graphAnalysis;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.qcpg.qcpg.dto.EntanglementPatternDTO;
import com.qcpg.qcpg.dto.graphAnalysis.*;
import com.qcpg.qcpg.model.graphAnalysis.*;
import com.qcpg.qcpg.repository.graphAnalysis.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Neo4jService {

    @Autowired
    private GraphAnalysisRepository nodeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GenericGraphDTO getGraph() {
        List<GenericNode> allNodes = new ArrayList<>();
        List<GenericRelationship> allEdges = new ArrayList<>();

        List<GenericNode> astNodes = nodeRepository.getAstNodes();
        List<Long> astNodeIds = astNodes.stream().map(GenericNode::getId).collect(Collectors.toList());
        allNodes.addAll(astNodes);
        allEdges.addAll(nodeRepository.getAstRelationships(astNodeIds));

        List<GenericNode> cfgNodes = nodeRepository.getCfgNodes();
        List<Long> cfgNodeIds = cfgNodes.stream().map(GenericNode::getId).collect(Collectors.toList());
        allNodes.addAll(cfgNodes);
        allEdges.addAll(nodeRepository.getCfgRelationships(cfgNodeIds));

        List<GenericNode> pdgNodes = nodeRepository.getPdgNodes();
        List<Long> pdgNodeIds = pdgNodes.stream().map(GenericNode::getId).collect(Collectors.toList());
        allNodes.addAll(pdgNodes);
        allEdges.addAll(nodeRepository.getPdgRelationships(pdgNodeIds));

        List<GenericNodeDTO> nodeDTOs = allNodes.stream()
                .distinct()
                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                .collect(Collectors.toList());

        List<GenericRelationshipDTO> edgeDTOs = allEdges.stream()
                .distinct()
                .map(edge -> modelMapper.map(edge, GenericRelationshipDTO.class))
                .collect(Collectors.toList());

        edgeDTOs.forEach(edgeDTO -> {
            edgeDTO.setSource(edgeDTO.getSource().toString());
            edgeDTO.setTarget(edgeDTO.getTarget().toString());
        });

        return new GenericGraphDTO(nodeDTOs, edgeDTOs, "entireGraph");
    }

    public GenericGraphDTO getAst() {
        List<GenericNode> nodes = nodeRepository.getAstNodes();
        List<Long> nodeIds = nodes.stream()
                .map(GenericNode::getId)
                .collect(Collectors.toList());
        List<GenericRelationship> edges = nodeRepository.getAstRelationships(nodeIds);

        List<GenericNodeDTO> nodeDTOs = nodes.stream()
                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                .collect(Collectors.toList());

        List<GenericRelationshipDTO> edgeDTOs = edges.stream()
                .map(edge -> modelMapper.map(edge, GenericRelationshipDTO.class))
                .collect(Collectors.toList());

        edgeDTOs.forEach(edgeDTO -> {
            edgeDTO.setSource(edgeDTO.getSource().toString());
            edgeDTO.setTarget(edgeDTO.getTarget().toString());
        });

        return new GenericGraphDTO(nodeDTOs, edgeDTOs, "ast");
    }

    public GenericGraphDTO getCfg() {
        List<GenericNode> nodes = nodeRepository.getCfgNodes();
        List<Long> nodeIds = nodes.stream()
                .map(GenericNode::getId)
                .collect(Collectors.toList());
        List<GenericRelationship> edges = nodeRepository.getCfgRelationships(nodeIds);

        List<GenericNodeDTO> nodeDTOs = nodes.stream()
                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                .collect(Collectors.toList());

        List<GenericRelationshipDTO> edgeDTOs = edges.stream()
                .map(edge -> modelMapper.map(edge, GenericRelationshipDTO.class))
                .collect(Collectors.toList());

        edgeDTOs.forEach(edgeDTO -> {
            edgeDTO.setSource(edgeDTO.getSource().toString());
            edgeDTO.setTarget(edgeDTO.getTarget().toString());
        });

        return new GenericGraphDTO(nodeDTOs, edgeDTOs, "cfg");
    }

    public GenericGraphDTO getPdg() {
        List<GenericNode> nodes = nodeRepository.getPdgNodes();
        List<Long> nodeIds = nodes.stream()
                .map(GenericNode::getId)
                .collect(Collectors.toList());
        List<GenericRelationship> edges = nodeRepository.getPdgRelationships(nodeIds);

        List<GenericNodeDTO> nodeDTOs = nodes.stream()
                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                .collect(Collectors.toList());

        List<GenericRelationshipDTO> edgeDTOs = edges.stream()
                .map(edge -> modelMapper.map(edge, GenericRelationshipDTO.class))
                .collect(Collectors.toList());

        edgeDTOs.forEach(edgeDTO -> {
            edgeDTO.setSource(edgeDTO.getSource().toString());
            edgeDTO.setTarget(edgeDTO.getTarget().toString());
        });

        return new GenericGraphDTO(nodeDTOs, edgeDTOs, "pdg");
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

            dto.setMaxDens(nodeRepository.getMaxDens(f));
            dto.setAvgDens(nodeRepository.getAvgDens(f));

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
            dto.setAvgCNOT(nodeRepository.getAvgCNOT(f));
            dto.setMaxCNOT(nodeRepository.getMaxCNOT(f));
            dto.setNoToff(nodeRepository.getNoToff(f));
            dto.setPQInToff(nodeRepository.getPQInToff(f));
            dto.setMaxToff(nodeRepository.getMaxToff(f));

            dto.setNoGates(nodeRepository.getNoGates(f));
            dto.setNoCGates(nodeRepository.getNoCGates(f));
            dto.setPSGates(nodeRepository.getPSGates(f));

            dto.setNoOr(nodeRepository.getNoOr(f));
            dto.setNoCOr(nodeRepository.getNoCOr(f));
            dto.setPQInOr(nodeRepository.getPQInOr(f));
            dto.setPQInCOr(nodeRepository.getPQInCOr(f));
            dto.setAvgOrD(nodeRepository.getAvgOrD(f));
            dto.setMaxOrD(nodeRepository.getMaxOrD(f));
            dto.setPSGatesOr(nodeRepository.getPSGatesOr(f));

            dto.setNoM(nodeRepository.getNoM(f));
            dto.setPQM(nodeRepository.getPQM(f));

            dto.setFile(f);

            result.add(dto);
        }
        return result;
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

            dto.setFile(f);

            result.add(dto);
        }
        return result;
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
}
