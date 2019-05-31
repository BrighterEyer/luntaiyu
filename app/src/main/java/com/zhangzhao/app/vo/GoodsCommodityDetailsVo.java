package com.zhangzhao.app.vo;

import com.zhangzhao.common.entity.Properties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class GoodsCommodityDetailsVo {

	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "副名称")
	private String viceName;

	@ApiModelProperty(value = "图片")
	private String img;

	@ApiModelProperty(value = "轮播图片")
	private String goodsImgs;

	@ApiModelProperty(value = "")
	private String commodityPrcie;

	@ApiModelProperty(value = "价格")
	private String price;

	@ApiModelProperty(value = "属性")
	private String property;

	@ApiModelProperty(value = "促销价")
	private String promotionPrice;

	@ApiModelProperty(value = "积分")
	private String integral;

	@ApiModelProperty(value = "销量")
	private String sales;

	@ApiModelProperty(value = "运费单价")
	private String freightPrcie;

	@ApiModelProperty(value = "满x免运费")
	private String freeShipping;

	@ApiModelProperty(value = "库存")
	private String inventory;

	@ApiModelProperty(value = "购买说明")
	private String buyInstructions;

	@ApiModelProperty(value = "安装类型 0-无需安装 1-安装")
	private String installationType;

	@ApiModelProperty(value = "保障")
	private List<GoodSecurityVo> goodSecurities;

	@ApiModelProperty(value = "评论")
	private List<CommentsVo> comments;

	@ApiModelProperty(value = "其他商品属性")
	private String propersieVoList;

	@ApiModelProperty(value = "活动")
	private ActivityVo activityVo;

	@ApiModelProperty(value = "商品属性")
	private List<ProperstiesVo> properties;

	@ApiModelProperty(value = "图文详情")
	private String context;

	@ApiModelProperty(value = "1-收藏")
	private String collect;

	@ApiModelProperty(value = "商品系列")
	private String series;
}
