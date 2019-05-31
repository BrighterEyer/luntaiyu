package com.zhangzhao.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class PrecontractEvaluateDto {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "状态 1-匿名 2-不匿名")
    private int status;

    @ApiModelProperty(value = "星级类型 1-1星 2-2星 3-3星 4-4星 5-5星")
    private int type;

}
