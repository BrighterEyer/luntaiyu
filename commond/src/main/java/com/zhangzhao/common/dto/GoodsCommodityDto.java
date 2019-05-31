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
public class GoodsCommodityDto {

	@ApiModelProperty
	private Long id;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "副名称")
	private String viceName;
	@ApiModelProperty(value = "图片")
	private String img;
	@ApiModelProperty(value = "热销 1")
	private String noyesHotsell;
	@ApiModelProperty(value = "优惠 0")
	private int discount;
	@ApiModelProperty(value = "一级分类")
	private Long oneClassId;
	@ApiModelProperty(value = "二级分类")
	private Long twoClassId;
	@ApiModelProperty(value = "三级分类")
	private Long threeClassId;
	@ApiModelProperty(value = "说明")
	private String buyInstructions;
	@ApiModelProperty(value = "安装类型")
	private String installationType;
	@ApiModelProperty(value = "安装费")
	private double installationFee;
	@ApiModelProperty(value = "质量 1")
	private int type;
	@ApiModelProperty(value = "花纹")
	private String figure;
	@ApiModelProperty(value = "商品图片")
	private String goodsImgs;
	@ApiModelProperty(value = "保障")
	private List<Long> goodSecuritie;

	@ApiModelProperty(value = "属性价格")
	private List<GoodsTablesDto> tables;

}
