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
public class OrderDetailsDto {

    @NotNull(message = "商品id不能为空")
    @ApiModelProperty(value = "商品id")
    private long id;

    @NotNull(message = "价格不能为空")
    @ApiModelProperty(value = "价格")
    private double prcie;

    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "数量")
    private int amount;

    @ApiModelProperty(value = "属性")
    private String property;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    @ApiModelProperty(value = "安装类型 0-到店安装 1-无需安装 2-上门安装")
    private String installationType;

}
