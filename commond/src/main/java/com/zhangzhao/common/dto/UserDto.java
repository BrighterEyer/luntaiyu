package com.zhangzhao.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class UserDto {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(value = "实名")
    private String reaName;

    @ApiModelProperty(value = "身份证号码")
    private String idCard;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "账号状态1-正常 2-封号")
    private int status;

    @ApiModelProperty(value = "类型 1-用户 2-师傅")
    private int type;

    @ApiModelProperty(value = "师傅申请状态 0-申请 1-通过 2-不通过")
    private int applyStatus;

}
