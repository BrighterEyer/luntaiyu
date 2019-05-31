/**
 * Copyright (C), 2018
 * FileName: CouponStatisticsVo
 * Author:   Administrator
 * Date:     2018/10/30 18:47
 * Description:
 */
package com.zhangzhao.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/10/30
 */
@Data
@Accessors(chain = true)
@ApiModel
public class CouponStatisticsVo {

    @ApiModelProperty(value = "总金额")
    private double zongJine;

    @ApiModelProperty(value = "优惠劵总数")
    private int couponZongshu;

    @ApiModelProperty(value = "代金券总数")
    private int couponDjin;

    @ApiModelProperty(value = "已使用总金额")
    private double yiShiYongz;

    @ApiModelProperty(value = "未使用总金额")
    private double weiShiYongz;

    @ApiModelProperty(value = "已过期总金额")
    private double yiGuoQiz;

}
