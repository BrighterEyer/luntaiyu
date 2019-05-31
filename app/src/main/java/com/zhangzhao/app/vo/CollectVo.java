package com.zhangzhao.app.vo;

import com.zhangzhao.common.entity.GoodsCommodity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CollectVo {
	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "商品")
	private GoodsCommodityVo goodsCommodity;
}
