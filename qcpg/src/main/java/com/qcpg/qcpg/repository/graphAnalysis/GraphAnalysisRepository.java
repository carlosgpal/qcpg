package com.qcpg.qcpg.repository.graphAnalysis;

import com.qcpg.qcpg.dto.EntanglementPatternDTO;
import com.qcpg.qcpg.dto.graphAnalysis.ExecEdgeDTO;
import com.qcpg.qcpg.dto.graphAnalysis.ExecEdgeDTOEx;
import com.qcpg.qcpg.dto.graphAnalysis.OpNodeDTO;
import com.qcpg.qcpg.dto.graphAnalysis.QubitNodeDTO;
import com.qcpg.qcpg.model.graphAnalysis.GenericNode;
import com.qcpg.qcpg.service.graphAnalysis.OperationProjection;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphAnalysisRepository extends Neo4jRepository<GenericNode, Long> {

  @Query("OPTIONAL MATCH (n) RETURN DISTINCT n.file")
  List<String> getDistinctFiles();

  @Query("OPTIONAL MATCH (qb:QUANTUM_BIT) WHERE qb.file = $file RETURN count(DISTINCT qb)")
  Long getWidth(String file);

  @Query("""
      OPTIONAL MATCH (qb:QUANTUM_BIT {file:$file})
      OPTIONAL MATCH (qb)<-[rel]-(op)
      WHERE rel.rel_type STARTS WITH 'QUANTUM_OPERAND'
         OR rel.rel_type = 'MEASUREMENT_SOURCE'
         OR rel.rel_type = 'MEASUREMENT_RESULT'
      WITH qb, count(DISTINCT op) AS totalOps
      RETURN max(totalOps)
      """)
  Long getDepth(String file);

  @Query("""
      MATCH (op)
      WHERE (op:QUANTUM_GATE OR op:QUANTUM_MEASURE OR op:QUANTUM_RESET OR op:QUANTUM_BARRIER)
        AND op.file = $file
      OPTIONAL MATCH (op)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:QUANTUM_OPERAND_3
                       |:MEASUREMENT_SOURCE|:MEASUREMENT_RESULT]
                   ->(qb:QUANTUM_BIT {file:$file})
      WITH op, collect(DISTINCT id(qb)) AS qubitIds
      RETURN id(op) AS opId, labels(op) AS labels, qubitIds AS qubitIds
      """)
  List<OperationProjection> getOperations(@Param("file") String file);

  @Query("""
          MATCH (op)
          WHERE (op:QUANTUM_GATE OR op:QUANTUM_MEASURE OR op:QUANTUM_RESET OR op:QUANTUM_BARRIER)
            AND op.file = $file
          OPTIONAL MATCH (op)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:QUANTUM_OPERAND_3
                           |:MEASUREMENT_SOURCE|:MEASUREMENT_RESULT]
                       ->(qb:QUANTUM_BIT {file:$file})
          OPTIONAL MATCH (parentAst)<-[:REFERENCE]-(op)
          WHERE parentAst.file = $file AND parentAst.code_line IS NOT NULL
          WITH op, collect(DISTINCT id(qb)) AS qubitIds,
               parentAst.code_line AS codeLine,
               parentAst.normalized_code_line AS normalizedCodeLine
          RETURN id(op) AS opId,
                 labels(op) AS labels,
                 qubitIds AS qubitIds,
                 codeLine AS lineOfCode,
                 normalizedCodeLine AS normalizedCodeLine
      """)
  List<OperationProjection> getOperationsForKnitting(@Param("file") String file);

  @Query("""
      MATCH (src)-[r:EXECUTION_ORDER]->(dst)
      WHERE (src:QUANTUM_GATE OR src:QUANTUM_MEASURE OR src:QUANTUM_RESET)
        AND (dst:QUANTUM_GATE OR dst:QUANTUM_MEASURE OR dst:QUANTUM_RESET)
        AND src.file = $file
        AND dst.file = $file
      RETURN id(src) AS sourceId, id(dst) AS targetId
      """)
  List<ExecEdgeDTOEx> getExecOrderEdges(@Param("file") String file);

  @Query("""
      OPTIONAL MATCH (op {file: $file})
      WHERE op:QUANTUM_GATE OR op:QUANTUM_MEASURE OR op:QUANTUM_RESET OR op:QUANTUM_BARRIER
      WITH op ORDER BY op.line_of_code ASC
      WITH collect(op) AS operations
      UNWIND operations AS op
      OPTIONAL MATCH path = (startOp)-[:EXECUTION_ORDER*]->(op)
      WHERE NOT ()-[:EXECUTION_ORDER]->(startOp)
      WITH op, length(path) AS depth
      WITH
        max(depth) AS maxLevel,
        collect({op: op, level: depth}) AS levels
      UNWIND levels AS level
      WITH maxLevel, level.level AS currentLevel, level.op AS currentOp
      WITH currentLevel, count(currentOp) AS opsInLevel
      RETURN
        max(opsInLevel) AS maxDens
            """)
  Double getMaxDens(String file);

  @Query("""
      OPTIONAL MATCH (op {file: $file})
      WHERE op:QUANTUM_GATE OR op:QUANTUM_MEASURE OR op:QUANTUM_RESET OR op:QUANTUM_BARRIER
      WITH op ORDER BY op.line_of_code ASC
      WITH collect(op) AS operations
      UNWIND operations AS op
      OPTIONAL MATCH path = (startOp)-[:EXECUTION_ORDER*]->(op)
      WHERE NOT ()-[:EXECUTION_ORDER]->(startOp)
      WITH op, length(path) AS depth
      WITH
        max(depth) AS maxLevel,
        collect({op: op, level: depth}) AS levels
      UNWIND levels AS level
      WITH maxLevel, level.level AS currentLevel, level.op AS currentOp
      WITH currentLevel, count(currentOp) AS opsInLevel
      RETURN
        avg(opsInLevel) AS avgDens
            """)
  Double getAvgDens(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_X) WHERE g.file = $file RETURN count(g)")
  Long getNoPx(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_Y) WHERE g.file = $file RETURN count(g)")
  Long getNoPy(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_Z) WHERE g.file = $file RETURN count(g)")
  Long getNoPz(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_X|QUANTUM_GATE_Y|QUANTUM_GATE_Z) WHERE g.file = $file RETURN count(g)")
  Long getTNoP(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_H) WHERE g.file = $file RETURN count(g)")
  Long getNoH(String file);

  @Query("""
      OPTIONAL MATCH (q:QUANTUM_BIT {file:$file})
      RETURN id(q) AS id,
             labels(q) AS labels
      """)
  List<QubitNodeDTO> getQubitsForFile(String file);

  @Query("""
      OPTIONAL MATCH (op)
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
      OPTIONAL MATCH (src)-[r:EXECUTION_ORDER]->(dst)
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
      OPTIONAL MATCH (g:QUANTUM_GATE {file: $file})
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
      OPTIONAL MATCH (g)
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

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_SWAP) WHERE g.file = $file RETURN count(g)")
  Long getNoSWAP(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_CX) WHERE g.file = $file RETURN count(g)")
  Long getNoCNOT(String file);

  @Query("""
          OPTIONAL MATCH (qb:QUANTUM_BIT {file:$file})
          WITH collect(DISTINCT qb) AS allQubits
          OPTIONAL MATCH (cn:QUANTUM_GATE_CX {file:$file})-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1]->(qb:QUANTUM_BIT {file:$file})
          WITH allQubits, collect(DISTINCT qb) AS affectedQubits
          RETURN CASE
               WHEN size(allQubits)=0 THEN 0.0
               ELSE 100.0 * size(affectedQubits) / size(allQubits)
               END AS percentage
      """)
  Double getPQInCNOT(String file);

  @Query("""
          OPTIONAL MATCH (qb:QUANTUM_BIT {file:$file})
          OPTIONAL MATCH (qb)<-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1]-(cn:QUANTUM_GATE_CX {file:$file})
          WITH qb, count(cn) AS cnotCount
          RETURN max(cnotCount) AS maxCNOT
      """)
  Long getMaxCNOT(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_CCX) WHERE g.file = $file RETURN count(g)")
  Long getNoToff(String file);

  @Query("""
          OPTIONAL MATCH (qb:QUANTUM_BIT {file:$file})
          WITH collect(DISTINCT qb) AS allQubits
          OPTIONAL MATCH (toff:QUANTUM_GATE_CCX {file:$file})-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2]->(qb:QUANTUM_BIT {file:$file})
          WITH allQubits, collect(DISTINCT qb) AS affectedQubits
          RETURN CASE
               WHEN size(allQubits)=0 THEN 0.0
               ELSE 100.0 * size(affectedQubits) / size(allQubits)
               END AS percentage
      """)
  Double getPQInToff(String file);

  @Query("""
          OPTIONAL MATCH (qb:QUANTUM_BIT {file:$file})
          OPTIONAL MATCH (qb)<-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2]-(toff:QUANTUM_GATE_CCX {file:$file})
          WITH qb, count(toff) AS toffCount
          RETURN max(toffCount) AS maxToff
      """)
  Long getMaxToff(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE) WHERE g.file = $file RETURN count(g)")
  Long getNoGates(String file);

  @Query("OPTIONAL MATCH (g:QUANTUM_GATE_CX|QUANTUM_GATE_CY|QUANTUM_GATE_CZ|QUANTUM_GATE_CH|QUANTUM_GATE_CRX|QUANTUM_GATE_CRY|QUANTUM_GATE_CRZ|QUANTUM_GATE_CU|QUANTUM_GATE_CU1|QUANTUM_GATE_CU2|QUANTUM_GATE_CU3|QUANTUM_GATE_CP|QUANTUM_GATE_CSWAP) WHERE g.file = $file RETURN count(g)")
  Long getNoCGates(String file);

  @Query("""
      OPTIONAL MATCH (g:QUANTUM_GATE {file: $file})
      OPTIONAL MATCH (g)-[r:QUANTUM_OPERAND_0|QUANTUM_OPERAND_1|QUANTUM_OPERAND_2]->(qb)
      WITH g, count(DISTINCT qb) AS qbits
      WITH sum(CASE WHEN qbits = 1 THEN 1 ELSE 0 END) AS singleQ,
           count(g) AS total
      RETURN CASE WHEN total = 0 THEN 0.0
            ELSE 100.0 * singleQ / total
       END AS percentage
        """)
  Double getPSGates(String file);

  @Query("""
          OPTIONAL MATCH (g)
          WHERE g.file = $file
            AND any(label IN labels(g)
                    WHERE label STARTS WITH 'QUANTUM_GATE_' AND toLower(label) CONTAINS 'oracle')
          RETURN count(DISTINCT g) AS numOracles
      """)
  Long getNoOr(String file);

  @Query("""
          OPTIONAL MATCH (qb:QUANTUM_BIT {file:$file})
          WITH collect(DISTINCT qb) AS allQubits
          OPTIONAL MATCH (g)
          WHERE g.file = $file
            AND any(label IN labels(g)
                    WHERE label STARTS WITH 'QUANTUM_GATE_' AND toLower(label) CONTAINS 'oracle')
          OPTIONAL MATCH (g)-[:QUANTUM_OPERAND_0|:QUANTUM_OPERAND_1|:QUANTUM_OPERAND_2|:QUANTUM_OPERAND_3]->(affected:QUANTUM_BIT {file:$file})
          WITH allQubits, collect(DISTINCT affected) AS affectedQubits
          RETURN CASE
               WHEN size(allQubits)=0 THEN 0.0
               ELSE 100.0 * size(affectedQubits) / size(allQubits)
               END AS percentage
      """)
  Double getPQInOr(String file);

  @Query("OPTIONAL MATCH (m:QUANTUM_MEASURE) WHERE m.file = $file RETURN count(m)")
  Long getNoM(String file);

  @Query("""
      OPTIONAL MATCH (m:QUANTUM_MEASURE)-[:MEASUREMENT_SOURCE]->(qb:QUANTUM_BIT)
      WHERE m.file = $file AND qb.file = $file
      WITH collect(DISTINCT qb) AS measuredQubits
      OPTIONAL MATCH (allQ:QUANTUM_BIT) WHERE allQ.file = $file
      WITH measuredQubits, collect(allQ) AS totalQ
      RETURN CASE WHEN size(totalQ) = 0 THEN 0.0
                  ELSE (toFloat(size(measuredQubits)) / size(totalQ)) * 100.0
             END
      """)
  Double getPQM(String file);

  @Query("""
      OPTIONAL MATCH (h)
      WHERE h.file = $file
        AND "QUANTUM_GATE_H" IN labels(h)
      OPTIONAL MATCH (cx)
      WHERE cx.file = $file
        AND "QUANTUM_GATE_CX" IN labels(cx)

      OPTIONAL MATCH p = (h)-[:EXECUTION_ORDER*]->(cx)

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

}