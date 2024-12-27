package com.qcpg.qcpg.dto.graphAnalysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasuresResponseDTO {
    private String id;
    private String qubit;
    private String classicBit;
}
