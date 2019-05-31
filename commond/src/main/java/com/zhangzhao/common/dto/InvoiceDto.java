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
public class InvoiceDto {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(value = "抬头",required = true)
    private String rise;

    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "税号")
    private String dutyParagraph;

    @ApiModelProperty(value = "类型 1-个人 2-企业",required = true)
    private int type;

}
