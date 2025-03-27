package com.qcpg.qcpg.service.graphAnalysis;

import java.util.List;

public class OperationProjection {
    private Long opId;
    private List<String> labels;
    private List<Long> qubitIds;
    private String lineOfCode;
    private String normalizedCodeLine;

    public OperationProjection(Long opId, List<String> labels, List<Long> qubitIds, String lineOfCode,
            String normalizedCodeLine) {
        this.opId = opId;
        this.labels = labels;
        this.qubitIds = qubitIds;
        this.lineOfCode = lineOfCode;
        this.normalizedCodeLine = normalizedCodeLine;
    }

    public Long getOpId() {
        return opId;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Long> getQubitIds() {
        return qubitIds;
    }

    public String getLineOfCode() {
        return lineOfCode;
    }

    public String getNormalizedCodeLine() {
        return normalizedCodeLine;
    }
}
