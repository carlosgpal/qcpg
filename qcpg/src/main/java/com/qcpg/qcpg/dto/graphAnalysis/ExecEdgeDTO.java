package com.qcpg.qcpg.dto.graphAnalysis;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecEdgeDTO {
    private Long relId;
    private String type;
    private Long sourceId;
    private Long targetId;
}
