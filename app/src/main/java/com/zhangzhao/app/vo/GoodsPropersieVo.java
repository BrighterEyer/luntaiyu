package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GoodsPropersieVo {

	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "值")
	private String val;
}
