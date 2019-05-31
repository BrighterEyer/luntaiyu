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
public class CouponDto {

    @ApiModelProperty
    private Long id;

    @NotNull(message = "名称不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @NotNull(message = "类型不能为空")
    @ApiModelProperty(value = "类型 1-优惠券 2-代金券", required = true)
    private int type;

    @NotNull(message = "金额不能为空")
    @ApiModelProperty(value = "金额", required = true)
    private double price;

    @NotNull(message = "时间不能为空")
    @ApiModelProperty(value = "到期时间", required = true)
    private String endTime;

    @ApiModelProperty(value = "指定商品id'")
    private String goodId;

    @ApiModelProperty(value = "指定用户id")
    private Long userId;

    @ApiModelProperty(value = "0-未使用 1-已使用 2-已过期")
    private int status;
}
