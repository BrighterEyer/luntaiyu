package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GoodsImgVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "图片地址")
    private String imgUrl;

    @ApiModelProperty(value = "跳转地址")
    private String imgJump;

    @ApiModelProperty(value = "名称")
    private String name;
}
