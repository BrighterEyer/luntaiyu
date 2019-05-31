package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserPaySettingVo {
    //验证用户支付密码
    @ApiModelProperty(value = "用户支付密码")
    private String paymentPassword;

}
