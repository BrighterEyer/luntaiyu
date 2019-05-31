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
public class ReservationsDto {
    @ApiModelProperty
    private Long id;
    @NotNull(message = "省不能为空")
    @ApiModelProperty(value = "省")
    private String province;
    @NotNull(message = "市不能为空")
    @ApiModelProperty(value = "市")
    private String district;
    @NotNull(message = "区不能为空")
    @ApiModelProperty(value = "区")
    private String city;
    @NotNull(message = "详细地址不能为空")
    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;
}
