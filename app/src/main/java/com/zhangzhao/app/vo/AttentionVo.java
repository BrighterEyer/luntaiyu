package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AttentionVo {
	@ApiModelProperty
	private String id;
}
