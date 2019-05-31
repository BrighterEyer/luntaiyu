/**
 * Copyright (C), 2018
 * FileName: StatisticsPercentageVO
 * Author:   Administrator
 * Date:     2018/10/15 12:04
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
 * @create 2018/10/15
 */
@Data
@Accessors(chain = true)
public class StatisticsPercentageVO {

    @ApiModelProperty(value = "好评率")
    private double haoPinglv;
    
    @ApiModelProperty(value = "准时率")
    private double zhunShilv;

}
