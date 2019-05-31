package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GoodSecurityVo {
	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "保障信息")
	private String name;

	@ApiModelProperty(value = "图片")
	private String img;
}
