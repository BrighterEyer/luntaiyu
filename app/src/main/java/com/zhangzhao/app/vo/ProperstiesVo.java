package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProperstiesVo {

	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "值")
	private String val;
}
