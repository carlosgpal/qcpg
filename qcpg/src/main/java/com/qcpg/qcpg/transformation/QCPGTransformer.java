package com.qcpg.qcpg.transformation;

import com.qcpg.qcpg.entities.QuantumProgram;
import com.qcpg.qcpg.entities.QCPG;
import com.qcpg.qcpg.entities.GenericNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QCPGTransformer {

    public QCPG transform(QuantumProgram quantumProgram) {
        QCPG qcpg = new QCPG();
        qcpg.setId(quantumProgram.getId());
        qcpg.setFilename("TODO");

        List<GenericNode> nodes = new ArrayList<>();

        GenericNode node = new GenericNode();
        node.setName("QuantumGate");
        node.setValue("H");

        nodes.add(node);

        qcpg.setNodes(nodes);

        return qcpg;
    }
}
