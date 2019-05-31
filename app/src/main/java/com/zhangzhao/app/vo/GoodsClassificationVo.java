package com.zhangzhao.app.vo;

import com.zhangzhao.common.entity.GoodsClassification;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GoodsClassificationVo {
	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "父id")
	private String fuClassId;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "一级图片")
	private String img;

	@ApiModelProperty(value = "下一级")
	private List<GoodsClassificationVo> two;
}
