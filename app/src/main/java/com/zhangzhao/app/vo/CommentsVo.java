package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhangzhao.common.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CommentsVo {
	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "内容")
	private String commcontent;

	@ApiModelProperty(value = "图片")
	private String imgs;

	@ApiModelProperty(value = "类型 0-好评 1-中评 2-差评")
	private String type;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "时间")
	private Date createTime;

	@ApiModelProperty(value = "评论人")
	private CommentsUserVo user;
}
