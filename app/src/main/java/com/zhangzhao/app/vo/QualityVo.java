package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class QualityVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "原因")
    private String cause;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "说明")
    private String explains;

    @ApiModelProperty(value = "报告")
    private String report;

    @ApiModelProperty(value = "状态 1-待跟进 2-处理中 3-已处理")
    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
