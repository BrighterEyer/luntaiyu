package com.zhangzhao.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 师傅申请
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class MasterTimeDto {

    @NotNull(message = "名称不能为空")
    @ApiModelProperty(value = "实名", required = true)
    private String reaName;

    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号码错误")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "师傅类型1-轮胎店/胎档 2-流动补胎 3-汽修厂 4-火补胎", required = true)
    private int typeMaster;

    @ApiModelProperty(value = "服务类型1-拆装轮胎2-普通补胎/冷补3火补胎4汽车维修", required = true)
    private int typeService;

    @ApiModelProperty(value = "省", required = true)
    private String province;

    @ApiModelProperty(value = "市", required = true)
    private String city;

    @ApiModelProperty(value = "区", required = true)
    private String district;

    @ApiModelProperty(value = "详细地址", required = true)
    private String detailedAddress;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "身份证正面", required = true)
    private String identityFront;

    @ApiModelProperty(value = "身份证反面", required = true)
    private String identityReverse;

    @ApiModelProperty(value = "用户上半身头像", required = true)
    private String upPhoto;

    @ApiModelProperty(value = "视频", required = true)
    private String video;

    @ApiModelProperty(value = "附件")
    private String rests;

}
