package com.zhangzhao.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class AddressDto {

    @ApiModelProperty(value = "地址id")
    private Long id;

    @NotNull(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotNull(message = "省不能为空")
    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "省ID")
    private Long provinceId;

    @NotNull(message = "市不能为空")
    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "市ID")
    private Long cityId;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号码错误")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "区ID")
    private Long districtId;

    @ApiModelProperty(value = "经度")
    private double longitude;

    @ApiModelProperty(value = "纬度")
    private double latitude;

    @ApiModelProperty(value = "详细地址")
    private String detailed;

    @ApiModelProperty(value = "1-默认")
    private int used;

}
