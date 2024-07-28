package com.qcpg.qcpg.transformation;

import com.qcpg.qcpg.entities.QuantumProgram;
import com.qcpg.qcpg.entities.QCPG;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class QCPGTransformer {

    public QCPG transform(QuantumProgram quantumProgram, String filename) {
        QCPG qcpg = new QCPG();
        qcpg.setFilename(filename);

        qcpg.setNodes(new ArrayList<>());
        qcpg.getNodes().addAll(quantumProgram.getQubits());
        qcpg.getNodes().addAll(quantumProgram.getClassicBits());
        qcpg.getNodes().addAll(quantumProgram.getMeasures());
        qcpg.getNodes().addAll(quantumProgram.getGates());
        qcpg.getNodes().addAll(quantumProgram.getASTNodes());

        return qcpg;
    }
}
