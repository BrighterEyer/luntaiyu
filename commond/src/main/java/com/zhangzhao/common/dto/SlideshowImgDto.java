package com.zhangzhao.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class SlideshowImgDto {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("跳转地址")
    private String imgJump;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("类型 1-首页 2-活动")
    private int type;

}
