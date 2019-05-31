package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
public class WeiXinOrderVo {
    @ApiModelProperty(value = "APPID")
    private String appId;

    @ApiModelProperty(value = "商户id")
    private String partnerId;

    @ApiModelProperty(value = "预支付id")
    private String prepayId;

    @ApiModelProperty(value = "固定值Sign=WXPay")
    private String packages;

    @ApiModelProperty(value = "随机字符串")
    private String nonceStr;

    @ApiModelProperty(value = "当前时间戳")
    private String timeStamp;

    @ApiModelProperty(value = "签名")
    private String sign;
}
