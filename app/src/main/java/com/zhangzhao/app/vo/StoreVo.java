package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StoreVo {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "详细地址")
    private String detailed;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "唯独")
    private String latitude;
}
