package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class IntegralVo {

    @ApiModelProperty
    private String id;
    @ApiModelProperty(value = "积分")
    private String integration;
    @ApiModelProperty(value = "类型 1-商品消费 2-积分兑换")
    private String type;
    @ApiModelProperty(value = "时间")
    private Date createTime;
}
