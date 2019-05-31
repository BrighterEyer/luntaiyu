package com.zhangzhao.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class TokenVo {

    @ApiModelProperty(value = "长  token")
    private String token;

    @ApiModelProperty(value = "短  token")
    private String tokenNext;


    @ApiModelProperty(value = "用户")
    private int userModel;

    @ApiModelProperty(value = "用户类型1-用户 2-师傅")
    private int type;

    @ApiModelProperty(value = "师傅")
    private int masterModel;


}
