package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserWalletVo {
    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "实名")
    private String identityName;//实名

    @ApiModelProperty(value = "银行卡")
    private BankCardVo bankCard;

    @ApiModelProperty(value = "钱包")
    private WalletVo wallet;

}
