package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ForRecordVo {
	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "图片")
	private String img;

	@ApiModelProperty(value = "积分")
	private String integral;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "时间")
	private Date createTime;

	@ApiModelProperty(value = "收货人")
	private String userName;

	@ApiModelProperty(value = "手机")
	private String phone;

	@ApiModelProperty(value = "地址")
	private String detailed;
}
