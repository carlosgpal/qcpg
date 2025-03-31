package com.qcpg.qcpg.dto.graphAnalysis;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericGraphDTO {
    private List<GenericNodeDTO> nodes;
    private List<GenericRelationshipDTO> edges;
    private String type;
}
