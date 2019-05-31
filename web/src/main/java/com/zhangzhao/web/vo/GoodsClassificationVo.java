package com.zhangzhao.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GoodsClassificationVo {

	@ApiModelProperty
	private Long id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "父id")
	private Long fuClassId;

	@ApiModelProperty(value = "图片")
	private String img;

	@ApiModelProperty(value = "名称")
	private List<GoodsClassificationVo> children;
}
