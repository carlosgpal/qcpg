package com.qcpg.qcpg.dto.graphAnalysis;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatternsByFileDTO {
    private String file;

    Map<Boolean, String> statePreparation;
    private boolean uniformSuperposition;
    List<Map<Integer, String>> creatingEntanglement;
    List<String> knittingSubcircuits;
}
