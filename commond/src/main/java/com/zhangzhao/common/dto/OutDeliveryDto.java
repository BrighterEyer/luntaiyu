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
public class OutDeliveryDto {

    @ApiModelProperty(value = "指定商品出库ID")
    private Long goodId;

    @ApiModelProperty(value = "轮胎编号")
    private String tyreNumber;

    @ApiModelProperty(value = "出库数量")
    private int deliveryCount;

    @ApiModelProperty(value = "出库原因")
    private String deliveryCause;

    @ApiModelProperty(value = "出库明细 1 -平台出库 2 -手动出库")
    private int deliveryDetail;

}
