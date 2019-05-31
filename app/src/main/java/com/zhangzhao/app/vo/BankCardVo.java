package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BankCardVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "卡号")
    private String cardNumber;

    @ApiModelProperty(value = "发卡银行")
    private String cardIssuingBank;

    @ApiModelProperty(value = "持卡人")
    private String bankName;

    @ApiModelProperty(value = "类型 1-储蓄 2-信用")
    private int type;
}
