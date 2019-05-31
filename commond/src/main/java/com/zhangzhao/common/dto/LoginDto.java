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
public class LoginDto {

    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号码错误")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;//密码
}
