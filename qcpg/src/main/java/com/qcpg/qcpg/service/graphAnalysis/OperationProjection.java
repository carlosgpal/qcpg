package com.qcpg.qcpg.service.graphAnalysis;

import java.util.List;

public class OperationProjection {
    private Long opId;
    private List<String> labels;
    private List<Long> qubitIds;

    public OperationProjection(Long opId, List<String> labels, List<Long> qubitIds) {
        this.opId = opId;
        this.labels = labels;
        this.qubitIds = qubitIds;
    }

    public Long getOpId() { return opId; }
    public List<String> getLabels() { return labels; }
    public List<Long> getQubitIds() { return qubitIds; }
}
