package net.edu.module.vo;


import lombok.Data;

@Data
public class RecordSampleVo {

    private Long recordId;

    private Long sampleId;

    private String runtime;

    private String memory;

    private Integer resultCode;
}
