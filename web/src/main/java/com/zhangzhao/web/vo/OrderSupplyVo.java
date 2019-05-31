package com.zhangzhao.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class OrderSupplyVo {

    @ApiModelProperty
    private Long id;
    @ApiModelProperty(value = "编号")
    private String orderNumber;
    @ApiModelProperty(value = "订单总价格")
    private double orderPrcie;
    @ApiModelProperty(value = "商品总价格")
    private double goodPrcie;
    @ApiModelProperty(value = "实际支付价格")
    private double paymentPrcie;
    @ApiModelProperty(value = "物流")
    private String reissue;
    @ApiModelProperty(value = "运费")
    private double freightPrcie;
    @ApiModelProperty(value = "状态 -2取消 -1-删除 1-待付款 2-待发货 3-待收货 4-待评价 5-已完成 6-退款 7-退货退款")
    private int status;
    @ApiModelProperty(value = "时间")
    private Date createTime;
}
