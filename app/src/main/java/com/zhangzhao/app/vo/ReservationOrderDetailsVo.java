/**
 * Copyright (C), 2018
 * FileName: ReservationOrderDetailsVo
 * Author:   Administrator
 * Date:     2018/9/29 9:55
 * Description:
 */
package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/9/29
 */
@Data
@Accessors(chain = true)
public class ReservationOrderDetailsVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "预约单号")
    private String reservationNumber;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "预约时间")
    private Date createTime;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "用户确认")
    private String userAffirm;

    @ApiModelProperty(value = "师傅确认")
    private String masterAffirm;

    @ApiModelProperty(value = "师傅名字")
    private Long masterName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否到店订单类型 1-是 2-不是")
    private int type;

    @ApiModelProperty(value = "状态 1-待接单 2-待服务 3-服务中 4-待评价 5-已完成")
    private int status;

    @ApiModelProperty(value = "用户头像")
    private String upPhoto;

    @ApiModelProperty(value = "预约费用")
    private double orderMoney;

    @ApiModelProperty(value = "补贴费")
    private double subsidyPrice;
}
