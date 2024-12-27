package com.qcpg.qcpg.dto.graphAnalysis;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericNodeDTO {
    private String id;
    private String name;
    private String file;
    private String code;
    private List<String> labels;
}