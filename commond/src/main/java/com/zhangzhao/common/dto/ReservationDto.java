package com.zhangzhao.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ReservationDto {

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "市")
    private String city;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "预约时间", required = true)
    private Date appointmentTime;

    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号码错误")
    @ApiModelProperty(value = "手机号", required = true)
    private String phoneNumber;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否到店单 1-是 2-不是")
    private int yesnoShopnote;

    @ApiModelProperty(value = "拆装条数")
    private int dismounting;

    @ApiModelProperty(value = "调位条数")
    private int positioning;

    @ApiModelProperty(value = "预约费用")
    private double orderMoney;

    @ApiModelProperty(value = "经度")
    private double longitude;

    @ApiModelProperty(value = "纬度")
    private double latitude;

    @ApiModelProperty(value = "门店")
    private Long storeId;

    @ApiModelProperty(value = "验证码", required = true)
    private String code;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @JsonIgnore
    private Long userId;

    @JsonIgnore
    private int status;

}
