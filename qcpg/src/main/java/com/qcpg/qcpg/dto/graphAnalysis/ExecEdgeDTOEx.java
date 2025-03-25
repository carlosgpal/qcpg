package com.qcpg.qcpg.dto.graphAnalysis;


public class ExecEdgeDTOEx {
    private Long sourceId;
    private Long targetId;
    
    public ExecEdgeDTOEx() {} // Constructor por defecto

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
}
