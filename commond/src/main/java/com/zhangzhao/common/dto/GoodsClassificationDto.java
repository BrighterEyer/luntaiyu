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
public class GoodsClassificationDto {
	@ApiModelProperty
	private Long id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "父id")
	private long fuClassId;

	@ApiModelProperty(value = "图片")
	private String img;
}
