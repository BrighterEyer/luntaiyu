package com.zhangzhao.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户搭档
 * 
 * 
 * @author Administrator
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserCooperate {

	public enum Status {
		yescoupon(1, "已返"), nocoupon(2, "未返");
		private int status;
		private String statusName;
		Status(int status, String statusName) {
			this.status = status;
			this.statusName = statusName;
		}
		public int getStatus() {
			return status;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '用户id'")
	private Long userId;

	@Column(name = "commodity_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '商品id'")
	private Long commodityId;

	@Column(name = "quantity", nullable = false, columnDefinition = "int(10) default 0 COMMENT '数量'")
	private int quantity;

	@Column(name = "nums", nullable = false, columnDefinition = "int(10) default 0 COMMENT '购买量'")
	private int nums;

	@Column(name = "preferential_priceZ", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '优惠价Z'")
	private double preferentialPriceZ;

	@Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-已返 2-未返'")
	private int status;

	@Column(name = "complete", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 0-未完成 1-已完成'")
	private int complete;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
	private Date createTime;
}
