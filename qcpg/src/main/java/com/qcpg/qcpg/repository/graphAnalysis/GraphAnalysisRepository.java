package com.qcpg.qcpg.repository.graphAnalysis;

import com.qcpg.qcpg.dto.EntanglementPatternDTO;
import com.qcpg.qcpg.dto.graphAnalysis.ExecEdgeDTO;
import com.qcpg.qcpg.dto.graphAnalysis.OpNodeDTO;
import com.qcpg.qcpg.dto.graphAnalysis.QubitNodeDTO;
import com.qcpg.qcpg.model.graphAnalysis.GenericNode;
import com.qcpg.qcpg.model.graphAnalysis.GenericRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphAnalysisRepository extends Neo4jRepository<GenericNode, Long> {

  // 1) Archivos/distintos ficheros
  @Query("MATCH (n) RETURN DISTINCT n.file")
  List<String> getDistinctFiles();

  // 2) Tamaño del circuito
  @Query("MATCH (qb:QUANTUM_BIT) WHERE qb.file = $file RETURN count(DISTINCT qb)")
  Long getWidth(String file);

  @Query("""
      MATCH (qb:QUANTUM_BIT {file:$file})
      OPTIONAL MATCH (qb)<-[rel]-(op)
      WHERE rel.rel_type STARTS WITH 'QUANTUM_OPERAND'
         OR rel.rel_type = 'MEASUREMENT_SOURCE'
         OR rel.rel_type = 'MEASUREMENT_RESULT'
      WITH qb, count(DISTINCT op) AS totalOps
      RETURN max(totalOps)
      """)
  Long getDepth(String file);

  // 3) Densidad del circuito
  @Query("""
      CALL apoc.cypher.run("
       MATCH (g:QUANTUM_GATE {file:$file})
       OPTIONAL MATCH (g)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:MEASUREMENT_SOURCE|:MEASUREMENT_RESULT]->(qb:QUANTUM_BIT)
       WITH g, collect(distinct qb.name) AS qubits
       WITH collect({id:id(g), qubits:qubits}) AS gates
       MATCH (p)-[:EXECUTION_ORDER]->(c)
       WHERE p.file=$file AND c.file=$file
       AND (p:QUANTUM_GATE OR p:QUANTUM_MEASURE OR p:QUANTUM_RESET OR p:QUANTUM_BARRIER)
       AND (c:QUANTUM_GATE OR c:QUANTUM_MEASURE OR c:QUANTUM_RESET OR c:QUANTUM_BARRIER)
       WITH gates, collect({from:id(p), to:id(c)}) AS edges
       CALL {
        WITH gates, edges
        WITH [] AS slots
        RETURN slots AS res
       }
       WITH res AS slots
       UNWIND slots AS s
       RETURN max(size(s)) AS densMax
      ", {file:$file}) YIELD value
      RETURN value.densMax
      """)
  Double getMaxDens(String file);

  @Query("""
      CALL apoc.cypher.run("
       MATCH (g:QUANTUM_GATE {file:$file})
       OPTIONAL MATCH (g)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:MEASUREMENT_SOURCE|:MEASUREMENT_RESULT]->(qb:QUANTUM_BIT)
       WITH g, collect(distinct qb.name) AS qubits
       WITH collect({id:id(g), qubits:qubits}) AS gates
       MATCH (p)-[:EXECUTION_ORDER]->(c)
       WHERE p.file=$file AND c.file=$file
         AND (p:QUANTUM_GATE OR p:QUANTUM_MEASURE OR p:QUANTUM_RESET OR p:QUANTUM_BARRIER)
         AND (c:QUANTUM_GATE OR c:QUANTUM_MEASURE OR c:QUANTUM_RESET OR c:QUANTUM_BARRIER)
       WITH gates, collect({from:id(p), to:id(c)}) AS edges
       CALL {
         WITH gates, edges
         WITH [] AS slots
         RETURN slots
      }
      UNWIND slots AS eachSlot
      WITH collect(eachSlot) AS slotList
      WITH reduce(acc=0, s IN slotList | acc + size(s)) AS total, size(slotList) AS sc
      RETURN CASE WHEN sc = 0 THEN 0.0 ELSE toFloat(total)/sc END AS densAvg
      ", {file:$file}) YIELD value
      RETURN value.densAvg
      """)
  Double getAvgDens(String file);

  // 4) Puertas de cúbit único
  @Query("MATCH (g:QUANTUM_GATE_X) WHERE g.file = $file RETURN count(g)")
  Long getNoPx(String file);

  @Query("MATCH (g:QUANTUM_GATE_Y) WHERE g.file = $file RETURN count(g)")
  Long getNoPy(String file);

  @Query("MATCH (g:QUANTUM_GATE_Z) WHERE g.file = $file RETURN count(g)")
  Long getNoPz(String file);

  @Query("MATCH (g:QUANTUM_GATE_X|QUANTUM_GATE_Y|QUANTUM_GATE_Z) WHERE g.file = $file RETURN count(g)")
  Long getTNoP(String file);

  @Query("MATCH (g:QUANTUM_GATE_H) WHERE g.file = $file RETURN count(g)")
  Long getNoH(String file);

  @Query("""
      MATCH (q:QUANTUM_BIT {file:$file})
      RETURN id(q) AS id,
             labels(q) AS labels
      """)
  List<QubitNodeDTO> getQubitsForFile(String file);

  @Query("""
      MATCH (op)
      WHERE (op:QUANTUM_GATE OR op:QUANTUM_MEASURE OR op:QUANTUM_RESET)
        AND op.file=$file
      OPTIONAL MATCH (op)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:MEASUREMENT_SOURCE|:MEASUREMENT_RESULT]
                   ->(qb:QUANTUM_BIT {file:$file})
      RETURN
        id(op) AS opId,
        labels(op) AS labels,
        collect(distinct id(qb)) AS qubitIds
      """)
  List<OpNodeDTO> getOpsWithLabelsAndQubits(String file);

  @Query("""
      MATCH (src)-[r:EXECUTION_ORDER]->(dst)
      WHERE src.file=$file AND dst.file=$file
        AND (src:QUANTUM_GATE OR src:QUANTUM_MEASURE OR src:QUANTUM_RESET)
        AND (dst:QUANTUM_GATE OR dst:QUANTUM_MEASURE OR dst:QUANTUM_RESET)
      RETURN id(r) AS relId,
             'EXECUTION_ORDER' AS type,
             id(src) AS sourceId,
             id(dst) AS targetId
      """)
  List<ExecEdgeDTO> getExecEdges(String file);

  @Query("""
      MATCH (g:QUANTUM_GATE {file: $file})
      OPTIONAL MATCH (g)-[r:QUANTUM_OPERAND_0|QUANTUM_OPERAND_1|QUANTUM_OPERAND_2|QUANTUM_OPERAND_3]->(qb:QUANTUM_BIT)
      WITH g, count(qb) AS totalQbits
      WHERE totalQbits = 1
        AND NOT (toLower(g.name) IN ['x','y','z','h'])
      RETURN count(DISTINCT g)
      """)
  Long getNoOtherSG(String file);

  @Query("""
      MATCH (g:QUANTUM_GATE {file: $file})
      OPTIONAL MATCH (g)-[r:QUANTUM_OPERAND_0|QUANTUM_OPERAND_1|QUANTUM_OPERAND_2|QUANTUM_OPERAND_3]->(qb:QUANTUM_BIT)
      WITH g, count(qb) AS totalQbits
      WHERE totalQbits = 1
      RETURN count(DISTINCT g)
      """)
  Long getTNoSG(String file);

  @Query("""
      MATCH (g)
      WHERE (
          g:QUANTUM_GATE_CX
          OR g:QUANTUM_GATE_CY
          OR g:QUANTUM_GATE_CZ
          OR g:QUANTUM_GATE_CH
          OR g:QUANTUM_GATE_CRX
          OR g:QUANTUM_GATE_CRY
          OR g:QUANTUM_GATE_CRZ
          OR g:QUANTUM_GATE_CU
          OR g:QUANTUM_GATE_CU1
          OR g:QUANTUM_GATE_CU2
          OR g:QUANTUM_GATE_CU3
          OR g:QUANTUM_GATE_CP
          OR g:QUANTUM_GATE_CSWAP
      )
      AND g.file = $file
      OPTIONAL MATCH (g)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1]->(qb:QUANTUM_BIT)
      WITH g, count(qb) AS totalQbits
      WHERE totalQbits = 2
      RETURN count(DISTINCT g)
      """)
  Long getTNoCSQG(String file);

  // 5) Puertas de cúbit múltiple
  @Query("MATCH (g:QUANTUM_GATE_SWAP) WHERE g.file = $file RETURN count(g)")
  Long getNoSWAP(String file);

  @Query("MATCH (g:QUANTUM_GATE_CX) WHERE g.file = $file RETURN count(g)")
  Long getNoCNOT(String file);

  @Query("""
      MATCH (cn:QUANTUM_GATE_CX)
      WHERE cn.file = $file
      MATCH (cn)-[]->(qb:QUANTUM_BIT)
      WITH collect(DISTINCT qb) AS qubitsInCnot
      MATCH (allQ:QUANTUM_BIT) WHERE allQ.file = $file
      WITH qubitsInCnot, collect(DISTINCT allQ) AS allQs
      RETURN CASE WHEN size(allQs) = 0 THEN 0.0
                  ELSE (toFloat(size(qubitsInCnot)) / size(allQs)) * 100.0
             END
      """)
  Double getPQInCNOT(String file);

  @Query("""
      MATCH (g:QUANTUM_GATE_CNOT)
      WHERE g.file = $file
      WITH g
      OPTIONAL MATCH (g)-[:QUANTUM_OPERAND_0|QUANTUM_OPERAND_1|QUANTUM_OPERAND_2|QUANTUM_OPERAND_3]->(qb:QUANTUM_BIT)
      WITH g, count(qb) AS qbitsAffected
      RETURN avg(qbitsAffected)
      """)
  Double getAvgCNOT(String file);

  @Query("""
      MATCH (g:QUANTUM_GATE_CX)
      WHERE g.file = $file
      WITH g
      OPTIONAL MATCH (g)-[:QUANTUM_OPERAND_0|QUANTUM_OPERAND_1|QUANTUM_OPERAND_2|QUANTUM_OPERAND_3]->(qb:QUANTUM_BIT)
      WITH g, count(qb) AS qbitsAffected
      RETURN max(qbitsAffected)
      """)
  Long getMaxCNOT(String file);

  @Query("MATCH (g:QUANTUM_GATE_CCX) WHERE g.file = $file RETURN count(g)")
  Long getNoToff(String file);

  @Query("""
      MATCH (t:QUANTUM_GATE_CCX)
      WHERE toLower(t.name) = 'toffoli' AND t.file = $file
      MATCH (t)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:QUANTUM_OPERAND_3]->(qb:QUANTUM_BIT)
      WITH collect(DISTINCT qb) AS qubitsInToff
      MATCH (allQ:QUANTUM_BIT) WHERE allQ.file = $file
      WITH qubitsInToff, collect(DISTINCT allQ) AS allQs
      RETURN CASE WHEN size(allQs) = 0 THEN 0.0
                  ELSE (toFloat(size(qubitsInToff)) / size(allQs)) * 100.0
             END
      """)
  Double getPQInToff(String file);

  @Query("""
      MATCH (g:QUANTUM_GATE_CCX)
      WHERE g.file = $file
      WITH g
      OPTIONAL MATCH (g)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:QUANTUM_OPERAND_3]->(qb:QUANTUM_BIT)
      WITH g, count(qb) AS qbitsAffected
      RETURN max(qbitsAffected)
      """)
  Long getMaxToff(String file);

  // 6) Todas las puertas
  @Query("MATCH (g:QUANTUM_GATE) WHERE g.file = $file RETURN count(g)")
  Long getNoGates(String file);

  @Query("MATCH (g:QUANTUM_GATE_CX|QUANTUM_GATE_CY|QUANTUM_GATE_CZ|QUANTUM_GATE_CH|QUANTUM_GATE_CRX|QUANTUM_GATE_CRY|QUANTUM_GATE_CRZ|QUANTUM_GATE_CU|QUANTUM_GATE_CU1|QUANTUM_GATE_CU2|QUANTUM_GATE_CU3|QUANTUM_GATE_CP|QUANTUM_GATE_CSWAP) WHERE g.file = $file RETURN count(g)")
  Long getNoCGates(String file);

  @Query("""
      MATCH (allG:QUANTUM_GATE {file: $file})
      WITH collect(distinct allG) AS allGates
      UNWIND allGates AS g
      OPTIONAL MATCH (g)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:QUANTUM_OPERAND_3]->(qb:QUANTUM_BIT)
      WITH allGates, g, count(distinct qb) AS qCount
      WHERE qCount = 1
      WITH allGates, collect(g) AS singleQGates
      RETURN CASE WHEN size(allGates) = 0 THEN 0.0
                  ELSE (toFloat(size(singleQGates)) / size(allGates)) * 100.0
             END
      """)
  Double getPSGates(String file);

  // 7) Oráculos
  @Query("""
      MATCH (o:AST_NODE {file:$file})
      WHERE o.descriptive_type IN ["COMPLEX_DEFINITION","GATE_DEFINITION"]
        AND toLower(o.display_name) CONTAINS "oracle"
      RETURN count(DISTINCT o)
      """)
  Long getNoOr(String file);

  @Query("""
      MATCH (o:AST_NODE {file:$file})
      WHERE o.descriptive_type IN ["COMPLEX_DEFINITION","GATE_DEFINITION"]
        AND toLower(o.display_name) CONTAINS "oracle"
      OPTIONAL MATCH (o)-[:AST*]->(qDec:AST_NODE {descriptive_type:"QUBIT_DECLARATION"})
      WITH o, sum(toInteger(qDec.qubit_size)) AS totalQbits
      WHERE totalQbits >= 2
      RETURN count(DISTINCT o)
      """)
  Long getNoCOr(String file);

  @Query("""
      MATCH (o:AST_NODE {file:$file})
      WHERE o.descriptive_type IN ["COMPLEX_DEFINITION","GATE_DEFINITION"]
        AND toLower(o.display_name) CONTAINS "oracle"
      OPTIONAL MATCH (o)-[:AST*]->(child:AST_NODE)
             -[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:QUANTUM_OPERAND_3]
             ->(qb:QUANTUM_BIT {file:$file})
      WITH collect(DISTINCT qb) AS qubitsInOr
      MATCH (allQ:QUANTUM_BIT {file:$file})
      WITH qubitsInOr, collect(DISTINCT allQ) AS allQs
      RETURN CASE WHEN size(allQs)=0 THEN 0.0
                  ELSE (toFloat(size(qubitsInOr)) / size(allQs)) * 100.0
             END
      """)
  Double getPQInOr(String file);

  @Query("""
      MATCH (o:AST_NODE {file:$file})
      WHERE o.descriptive_type IN ["COMPLEX_DEFINITION","GATE_DEFINITION"]
        AND toLower(o.display_name) CONTAINS "oracle"
      OPTIONAL MATCH (o)-[:AST*]->(qDec:AST_NODE {descriptive_type:"QUBIT_DECLARATION"})
      WITH o, sum(toInteger(qDec.qubit_size)) AS totalQbits
      WHERE totalQbits >= 2
        // Hasta aquí: oráculos con 2+ qubits
      OPTIONAL MATCH (o)-[:AST*]->(child:AST_NODE)
             -[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:QUANTUM_OPERAND_3]
             ->(qb:QUANTUM_BIT {file:$file})
      WITH collect(DISTINCT qb) AS qubitsInCOr
      MATCH (allQ:QUANTUM_BIT {file:$file})
      WITH qubitsInCOr, collect(DISTINCT allQ) AS allQs
      RETURN CASE WHEN size(allQs)=0 THEN 0.0
                  ELSE (toFloat(size(qubitsInCOr)) / size(allQs)) * 100.0
             END
      """)
  Double getPQInCOr(String file);

  @Query("""
      MATCH (o:AST_NODE {file:$file})
      WHERE o.descriptive_type IN ["COMPLEX_DEFINITION","GATE_DEFINITION"]
        AND toLower(o.display_name) CONTAINS "oracle"
      OPTIONAL MATCH (o)-[:AST*]->(n:AST_NODE {descriptive_type:"GATE_CALL"})
      WITH o, count(n) AS gateCount
      WITH avg(gateCount) AS avgOrDepth
      RETURN avgOrDepth
      """)
  Double getAvgOrD(String file);

  @Query("""
      MATCH (o:AST_NODE {file:$file})
      WHERE o.descriptive_type IN ["COMPLEX_DEFINITION","GATE_DEFINITION"]
        AND toLower(o.display_name) CONTAINS "oracle"
      OPTIONAL MATCH (o)-[:AST*]->(n:AST_NODE {descriptive_type:"GATE_CALL"})
      WITH o, count(n) AS gateCount
      WITH max(gateCount) AS maxOrDepth
      RETURN maxOrDepth
      """)
  Long getMaxOrD(String file);

  @Query("""
      MATCH (o:AST_NODE {file:$file})
      WHERE o.descriptive_type IN ["COMPLEX_DEFINITION","GATE_DEFINITION"]
        AND toLower(o.display_name) CONTAINS "oracle"
      OPTIONAL MATCH (o)-[:AST*]->(gc:AST_NODE {descriptive_type:"GATE_CALL"})
      OPTIONAL MATCH (gc)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2]->(qb:QUANTUM_BIT {file:$file})
      WITH o, gc, count(qb) AS qCount
      WITH o,
           collect(gc) AS allGatesOr,
           collect(CASE WHEN qCount=1 THEN gc END) AS singleQGatesOr
      WITH o, allGatesOr, singleQGatesOr
      RETURN CASE WHEN size(allGatesOr)=0 THEN 0.0
                  ELSE (toFloat(size(singleQGatesOr)) / size(allGatesOr)) * 100.0
             END
      """)
  Double getPSGatesOr(String file);

  @Query("MATCH (m:QUANTUM_MEASURE) WHERE m.file = $file RETURN count(m)")
  Long getNoM(String file);

  @Query("""
      MATCH (m:QUANTUM_MEASURE)-[:MEASUREMENT_SOURCE]->(qb:QUANTUM_BIT)
      WHERE m.file = $file AND qb.file = $file
      WITH collect(DISTINCT qb) AS measuredQubits
      MATCH (allQ:QUANTUM_BIT) WHERE allQ.file = $file
      WITH measuredQubits, collect(allQ) AS totalQ
      RETURN CASE WHEN size(totalQ) = 0 THEN 0.0
                  ELSE (toFloat(size(measuredQubits)) / size(totalQ)) * 100.0
             END
      """)
  Double getPQM(String file);

  @Query("""
    MATCH (h)
    WHERE h.file = $file
      AND "QUANTUM_GATE_H" IN labels(h)
    MATCH (cx)
    WHERE cx.file = $file
      AND "QUANTUM_GATE_CX" IN labels(cx)
    
    MATCH p = (h)-[:EXECUTION_ORDER*]->(cx)
    
    OPTIONAL MATCH (h)-[:QUANTUM_OPERAND_0|QUANTUM_OPERAND_1]->(qA:QUANTUM_BIT {file:$file})
    OPTIONAL MATCH (cx)-[:QUANTUM_OPERAND_0|QUANTUM_OPERAND_1]->(qB:QUANTUM_BIT {file:$file})
    
    WITH h, cx, qA, qB, p
    WHERE qA IS NOT NULL
      AND qB IS NOT NULL
      AND qA <> qB
      AND NONE ( midNode IN nodes(p)[1..-1]
                 WHERE midNode <> h
                   AND midNode <> cx
                   AND (
                        (midNode)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1]->(qA)
                         OR
                        (midNode)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1]->(qB)
                       )
               )
    RETURN 
      
      {
        id: id(h),
        name: h.name,
        file: h.file,
        code: h.code_line,
        labels: labels(h)
      } AS hadamardGate,
    
      {
        id: id(cx),
        name: cx.name,
        file: cx.file,
        code: cx.code_line,
        labels: labels(cx)
      } AS cxGate,
    
      {
        id: id(qA),
        name: qA.name,
        file: qA.file,
        code: qA.code_line,
        labels: labels(qA)
      } AS qubitControl,
    
      {
        id: id(qB),
        name: qB.name,
        file: qB.file,
        code: qB.code_line,
        labels: labels(qB)
      } AS qubitTarget
    """)
    List<EntanglementPatternDTO> getCreatingEntanglementPatterns(String file);

  @Query("""
      MATCH (root:PROGRAM_ROOT)
      OPTIONAL MATCH (root)-[:AST*]->(child)
      WITH root, COLLECT(DISTINCT child) AS children
      UNWIND [root] + children AS node
      RETURN DISTINCT id(node) AS id,
                      node.display_name AS fullName,
                      node.file AS file,
                      node.code_line AS code,
                      labels(node) AS labels
      """)
  List<GenericNode> getAstNodes();

  @Query("""
      MATCH (sourceNode)-[r:AST]->(targetNode)
      WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds
      RETURN id(r) AS id,
             type(r) AS type,
             id(sourceNode) AS sourceNodeId,
             id(targetNode) AS targetNodeId
      """)
  List<GenericRelationship> getAstRelationships(List<Long> selectedNodeIds);

  @Query("""
      MATCH (node)
      WHERE 'CFG_ENTRY' IN labels(node)
         OR 'CFG_EXIT' IN labels(node)
         OR 'STATEMENT' IN labels(node)
      RETURN DISTINCT id(node) AS id,
                      node.display_name AS fullName,
                      node.file AS file,
                      node.code_line AS code,
                      labels(node) AS labels
      """)
  List<GenericNode> getCfgNodes();

  @Query("""
      MATCH (sourceNode)-[r:CFG|FLOWS_TO*]->(targetNode)
      WHERE id(sourceNode) IN $selectedNodeIds
        AND id(targetNode) IN $selectedNodeIds
      RETURN id(r) AS id,
             type(r) AS type,
             id(sourceNode) AS sourceNodeId,
             id(targetNode) AS targetNodeId
      """)
  List<GenericRelationship> getCfgRelationships(List<Long> selectedNodeIds);

  @Query("""
      MATCH (node)
      WHERE 'PDG' IN labels(node)
      RETURN DISTINCT id(node) AS id,
                      node.display_name AS fullName,
                      node.file AS file,
                      node.code_line AS code,
                      labels(node) AS labels
      """)
  List<GenericNode> getPdgNodes();

  @Query("""
      MATCH (sourceNode)-[r:PDG|PDG_CONTROL|PDG_TRUE|PDG_FALSE]->(targetNode)
      WHERE id(sourceNode) IN $selectedNodeIds
        AND id(targetNode) IN $selectedNodeIds
      RETURN id(r) AS id,
             type(r) AS type,
             id(sourceNode) AS sourceNodeId,
             id(targetNode) AS targetNodeId
      """)
  List<GenericRelationship> getPdgRelationships(List<Long> selectedNodeIds);
}
