package com.qcpg.qcpg.dto.graphAnalysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericRelationshipDTO {
    private String id;
    private String type;
    private String source;
    private String target;
}
