package com.qcpg.qcpg.dto.graphAnalysis;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleNodeDTO {
    private Long id;
    private String name;
    private String file;
    private String code;
    private List<String> labels;
}