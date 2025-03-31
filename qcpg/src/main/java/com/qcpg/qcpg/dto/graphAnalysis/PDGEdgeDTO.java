package com.qcpg.qcpg.dto.graphAnalysis;

public class PDGEdgeDTO {
    private Long sourceId;
    private Long targetId;
    private String relType;

    public Long getSourceId() {
        return sourceId;
    }
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }
    public Long getTargetId() {
        return targetId;
    }
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
    public String getRelType() {
        return relType;
    }
    public void setRelType(String relType) {
        this.relType = relType;
    }
}
