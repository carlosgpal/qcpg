package com.qcpg.qcpg.dto.graphAnalysis;

import lombok.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpNodeDTO {
    private Long opId;
    private List<String> labels;
    private List<Long> qubitIds;
}

