package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ActivityVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "图片")
    private String img;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "图文详情")
    private String context;

    @ApiModelProperty(value = "视频")
    private String videoUrl;

    @ApiModelProperty(value = "商品id")
    private String goodId;

    @ApiModelProperty(value = "优惠价")
    private String price;
}
