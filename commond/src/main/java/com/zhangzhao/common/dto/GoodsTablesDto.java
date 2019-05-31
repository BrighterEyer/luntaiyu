package com.zhangzhao.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class GoodsTablesDto {

	@ApiModelProperty(value = "价格")
	private double price;
	@ApiModelProperty(value = "促销价")
	private double promotionPrice;
	@ApiModelProperty(value = "搭档价格")
	private double cooperatePrcie;
	@ApiModelProperty(value = "属性")
	private List<Long> propert;
	@ApiModelProperty(value = "会员X价格")
	private double memberXprcie;
	@ApiModelProperty(value = "会员Y价格")
	private double memberYprcie;
	@ApiModelProperty(value = "运费单价")
	private double freightPrcie;
	@ApiModelProperty(value = "满x免运费")
	private int freeShipping;
	@ApiModelProperty(value = "库存")
	private int inventory;
}
