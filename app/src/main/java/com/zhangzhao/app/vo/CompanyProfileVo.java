package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyProfileVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "词汇")
    private String keyword;

    @ApiModelProperty(value = "热门城市")
    private String city;
}
