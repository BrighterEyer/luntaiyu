package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserPersonalVo {
    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "名称")
    private String userName;

    @ApiModelProperty(value = "性别 0-男 1-女")
    private String gender;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "车重")
    private String vehicleWeight;

    @ApiModelProperty(value = "路况")
    private String roadCondition;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "会员时间")
    private Date membershipTime;

    @ApiModelProperty(value = "会员类型 0-普通 1-会员 2-搭档")
    private String member;

    @ApiModelProperty(value = "车型")
    private String vehicle;

    @ApiModelProperty(value = "积分")
    private String integral;
}
