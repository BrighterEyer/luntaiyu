package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TradingRecordVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "金额")
    private String money;//金额

    @ApiModelProperty(value = "扣款说明")
    private String instructions;


}
