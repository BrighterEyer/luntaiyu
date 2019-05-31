package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PresentWodeVo {

    @ApiModelProperty(value = "我的积分")
    private String integral;

    @ApiModelProperty(value = "礼品")
    private List<PresentVo> presentVos;
}
