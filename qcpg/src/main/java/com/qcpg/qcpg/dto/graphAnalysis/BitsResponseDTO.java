package com.qcpg.qcpg.dto.graphAnalysis;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BitsResponseDTO {
    private String id;
    private List<String> classicBits;
}
