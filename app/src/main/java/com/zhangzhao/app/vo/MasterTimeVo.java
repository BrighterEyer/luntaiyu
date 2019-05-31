package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class MasterTimeVo {
	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "简介内容")
	private String profile;

	@ApiModelProperty(value = "协议")
	private String agreement;

	@ApiModelProperty(value = "客服")
	private String customer;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "上班时间")
	private Date gotoWork;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "下班时间")
	private Date getoffWork;
}
