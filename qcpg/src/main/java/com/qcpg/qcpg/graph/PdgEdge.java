package com.qcpg.qcpg.graph;

import lombok.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdgEdge {
    private PdgNode from;
    private PdgNode to;
    @Builder.Default
    private List<String> labels = new ArrayList<>();
    @Builder.Default
    private Map<String,Object> properties = Map.of();
}
