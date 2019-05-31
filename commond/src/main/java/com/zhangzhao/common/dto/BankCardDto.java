package com.zhangzhao.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BankCardDto {

    @ApiModelProperty
    private int id;

    @NotNull(message = "卡号不能为空")
    @ApiModelProperty(value = "卡号")
    private String cardNumber;
    @NotNull(message = "发卡银行不能为空")
    @ApiModelProperty(value = "发卡银行")
    private String cardIssuingBank;
    @NotNull(message = "持卡人不能为空")
    @ApiModelProperty(value = "持卡人")
    private String bankName;
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号码错误")
    @ApiModelProperty(value = "手机号码")
    private String phone;
    @NotNull(message = "类型不能为空")
    @ApiModelProperty(value = "类型 1-储蓄 2-信用")
    private int type;

}
