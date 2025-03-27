package com.qcpg.qcpg.dto.graphAnalysis;

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
public class MetricsByFileDTO {
    private String file;

    private Long width;
    private Long depth;

    private Long maxDens;
    private Double avgDens;

    private Long noPx;
    private Long noPy;
    private Long noPz;
    private Long tNoP;
    private Long noH;
    private Double pSposQ;
    private Long noOtherSG;
    private Long tNoSG;
    private Long tNoCSQG;

    private Long noSWAP;
    private Long noCNOT;
    private Double pQInCNOT;
    private Long maxCNOT;
    private Long noToff;
    private Double pQInToff;
    private Long maxToff;

    private Long noGates;
    private Long noCGates;
    private Double pSGates;

    private Long noOr;
    private Double pQInOr;

    private Long noM;
    private Double pQM;
}
