package com.qcpg.qcpg.dto;

import com.qcpg.qcpg.dto.graphAnalysis.SimpleNodeDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntanglementPatternDTO {
    private SimpleNodeDTO hadamardGate;
    private SimpleNodeDTO cxGate;
    private SimpleNodeDTO qubitControl;
    private SimpleNodeDTO qubitTarget;
}