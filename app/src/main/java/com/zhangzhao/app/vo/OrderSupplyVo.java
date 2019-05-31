package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderSupplyVo {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(value = "订单价格")
    private String orderPrcie;

    @ApiModelProperty(value = "状态 -2取消 -1-删除 1-待付款 2-待发货 3-待收货 4-待评价 5-已完成 6-退款 7-退货退款")
    private String status;

    @ApiModelProperty(value = "运费")
    private String freightPrcie;

    @ApiModelProperty(value = "积分")
    private String integral;

    @ApiModelProperty(value = "类型 1-不参与优惠 2-参与优惠")
    private String type;

    @ApiModelProperty(value = "详细")
    private List<OrderDetailsVo> orderDetails;

    @ApiModelProperty(value = "商品属性")
    private String property;

    @ApiModelProperty(value = "安装类型 0-到店安装 1-无需安装 2-上门安装")
    private String installationtype;
}
