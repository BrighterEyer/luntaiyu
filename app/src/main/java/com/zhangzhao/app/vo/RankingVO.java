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
public class RankingVO {

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "好评率")
    private String haoPinglv;

    @ApiModelProperty(value = "类型 1月 2季度 3年")
    private int type;


}
