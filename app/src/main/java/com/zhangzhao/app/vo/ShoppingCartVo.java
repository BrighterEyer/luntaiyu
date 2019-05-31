package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShoppingCartVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "数量")
    private String amount;

    @ApiModelProperty(value = "属性")
    private String property;

    @ApiModelProperty(value = "积分")
    private String integral;
}
