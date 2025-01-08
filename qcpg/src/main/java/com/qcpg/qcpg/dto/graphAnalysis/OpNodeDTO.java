package com.qcpg.qcpg.dto.graphAnalysis;

import lombok.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpNodeDTO {
    private Long opId;
    private List<String> labels;      // Ej. ["QUANTUM_GATE","QUANTUM_GATE_H"]
    private List<Long> qubitIds;      // IDs de qubits afectados
}

