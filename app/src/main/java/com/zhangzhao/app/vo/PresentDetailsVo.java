package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PresentDetailsVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "内容")
    private String context;

    @ApiModelProperty(value = "积分")
    private String integral;

    @ApiModelProperty(value = "默认地址")
    private AddressVo addressVo;
}
