package com.zhangzhao.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class OutDeliveryVo {
    @ApiModelProperty
    private String id;

    @ApiModelProperty("指定商品ID")
    private Long goodId;

    @ApiModelProperty(value = "标题")
    private String name;

    @ApiModelProperty(value = "副标题")
    private String viceName;

    @ApiModelProperty(value = "出库明细 1- 平台 2 手动")
    private int deliveryDetail;

    @ApiModelProperty(value = "出库数量")
    private int deliveryCount;

    @ApiModelProperty(value = "出库原因")
    private String deliveryCause;

    @ApiModelProperty(value = "轮胎编号")
    private String tyreNumber;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
