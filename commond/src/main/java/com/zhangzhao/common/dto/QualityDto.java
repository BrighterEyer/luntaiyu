package com.zhangzhao.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class QualityDto {

    @NotNull(message = "原因不能为空")
    @ApiModelProperty(value = "原因")
    private String cause;

    @NotNull(message = "图片不能为空")
    @ApiModelProperty(value = "图片")
    private String img;

    @NotNull(message = "说明不能为空")
    @ApiModelProperty(value = "说明")
    private String explains;

    @ApiModelProperty(value = "跟进流程")
    private String procedures;

    @ApiModelProperty(value = "报告")
    private String report;

    @ApiModelProperty(value = "状态 1-待跟进 2-处理中 3-已处理")
    private int status;
}
