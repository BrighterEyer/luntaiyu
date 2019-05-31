package com.zhangzhao.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhangzhao.common.entity.OrderDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class OrderSupplyDto {

    @ApiModelProperty
    private int id;

    @NotNull(message = "收货地址不能为空")
    @ApiModelProperty(value = "收货地址id",required = true)
    private Long addressId;

    @NotNull(message = "订单总价格不能为空")
    @ApiModelProperty(value = "订单总价格",required = true)
    private double orderPrcie;

    @ApiModelProperty(value = "商品总价格")
    private double goodPrcie;

    @ApiModelProperty(value = "安装费")
    private double installPrcie;

    @ApiModelProperty(value = "运费")
    private double freightPrcie;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "积分")
    private Long integral;

    @ApiModelProperty(value = "发票id")
    private Long invoiceId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "预安装时间")
    private Date preInstallationTime;

    @ApiModelProperty(value = "门店")
    private Long storeId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "上门安装时间")
    private Date storeInstallationTime;

    @ApiModelProperty(value = "商品详情")
    private List<OrderDetailsDto> orderDetails;
}
