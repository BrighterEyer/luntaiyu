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
public class UserStatisticsVo {
    //订单
    @ApiModelProperty(value = "总成交金额")
    private double zongChengjiaoe;

    @ApiModelProperty(value = "成交次数")
    private int chengJiaocishu;

    @ApiModelProperty(value = "年")
    private double year;

    @ApiModelProperty(value = "季度")
    private double quarter;

    @ApiModelProperty(value = "月")
    private double month;
}
