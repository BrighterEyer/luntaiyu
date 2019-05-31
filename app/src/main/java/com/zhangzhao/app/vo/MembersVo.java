package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MembersVo {
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "会员类型")
    private int member;

    @ApiModelProperty(value = "免费次数")
    private int gratisTime;
}
