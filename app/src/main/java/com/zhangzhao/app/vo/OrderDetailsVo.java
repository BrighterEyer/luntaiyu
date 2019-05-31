package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderDetailsVo {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "支付价格")
    private double paymentPrcie;

    @ApiModelProperty(value = "数量")
    private String amount;

    @ApiModelProperty(value = "积分")
    private String integral;

    @ApiModelProperty(value = "商品id")
    private Long goodsCommodityId;

    @ApiModelProperty(value = "胎号")
    private String fetalNumber;

    @ApiModelProperty(value = "型号")
    private String model;

    @ApiModelProperty(value = "安装类型")
    private String installationType;

    @ApiModelProperty(value = "商品属性")
    private String property;

    @ApiModelProperty(value = "退款金额")
    private String orderPrcie;

    @ApiModelProperty(value = "运费")
    private String freightPrcie;

    @ApiModelProperty(value = "总价")
    private String prcieAll;

}
