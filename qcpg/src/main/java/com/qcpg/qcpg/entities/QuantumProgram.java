package com.qcpg.qcpg.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantumProgram {
    private Long id;
    private String code;
    List<GenericNode> qubits;
    List<GenericNode> classicBits;
    List<GenericNode> gates;
    List<GenericNode> measures;
}
