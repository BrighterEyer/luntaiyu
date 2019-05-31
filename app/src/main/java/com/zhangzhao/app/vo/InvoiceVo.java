package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InvoiceVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "抬头")
    private String rise;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "税号")
    private String dutyParagraph;

    @ApiModelProperty(value = "类型 1-个人 2-企业")
    private String type;
}
