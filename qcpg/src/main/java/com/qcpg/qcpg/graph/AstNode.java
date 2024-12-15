package com.qcpg.qcpg.graph;

import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AstNode {
    private long id;

    @Builder.Default
    private List<String> labels = new ArrayList<>();

    @Builder.Default
    private Map<String,Object> properties = Map.of();

    @Builder.Default
    private List<AstNode> children = new ArrayList<>();
}
