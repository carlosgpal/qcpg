package com.qcpg.qcpg.dto.graphAnalysis;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QubitNodeDTO {
    private Long id;
    private List<String> labels;
}
