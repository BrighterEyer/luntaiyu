package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserVo {
    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "类型 1-用户 2-师傅")
    private String type;
}
