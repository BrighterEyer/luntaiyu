package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class FlowVo {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(value = "跟进流程")
    private String procedures;

    @ApiModelProperty(value = "状态 1-待跟进 2-处理中 3-已处理")
    private int status;

    @ApiModelProperty(value = "原因")
    private String cause;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
