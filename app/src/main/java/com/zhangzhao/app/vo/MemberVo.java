package com.zhangzhao.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MemberVo {
	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "价格")
	private String memberPrcie;

	@ApiModelProperty(value = "说明")
	private String instructions;

	@ApiModelProperty(value = "会员时限 1-1月 2-3月 3-6月 4-12月")
	private String timeLimit;
}
