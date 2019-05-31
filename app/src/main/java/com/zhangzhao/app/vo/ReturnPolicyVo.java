package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhangzhao.common.entity.OrderSupply;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ReturnPolicyVo {
	@ApiModelProperty
	private String id;

	@ApiModelProperty(value = "类型 1-退款 2-补发 3-退货退款")
	private String type;

	@ApiModelProperty(value = "补发物流单号")
	private String returnsNumber;

	@ApiModelProperty(value = "状态 1-待商家确认退款/退货退款待确认/待商家确认补发 2-退款已确认/退货退款已确认/补发已确认 3-退货退款中/补发收货 4-退货退款已完成")
	private String status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "倒计时")
	private Date refundDate;

	@ApiModelProperty(value = "售后订单商品")
	private OrderSupplyDetailVo orderSupply;

	@ApiModelProperty(value = "物流名称")
	private String returnsName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "退货时间")
	private Date createTime = new Date();

	@ApiModelProperty(value = "物流公司")
	private String company;


}
