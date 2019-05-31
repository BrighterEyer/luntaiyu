package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WalletVo {
    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "余额")
    private String balance;//余额

}
