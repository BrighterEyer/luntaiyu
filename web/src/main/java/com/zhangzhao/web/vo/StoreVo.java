package com.zhangzhao.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StoreVo {

    @ApiModelProperty
    private String id;
}
