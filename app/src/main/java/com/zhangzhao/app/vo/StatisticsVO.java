/**
 * Copyright (C), 2018
 * FileName: StatisticsVO
 * Author:   Administrator
 * Date:     2018/10/12 14:30
 * Description:
 */
package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/10/12
 */
@Data
@Accessors(chain = true)
public class StatisticsVO {

    @ApiModelProperty(value = "累计订单数")
    private int orderFrequency;

    @ApiModelProperty(value = "今日完成订单数")
    private int daysOrderFrequency;

    @ApiModelProperty(value = "累计收支")
    private double addShouzhi;

    @ApiModelProperty(value = "昨日收支")
    private double yesterdayShouzhi;



}
