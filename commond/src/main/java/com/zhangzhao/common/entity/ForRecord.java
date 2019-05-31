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
 * 兑换记录
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
public class ForRecord {

	public enum Status {
		THROUGH(1, "通过"), NOT_THROUGH(2, "不通过");
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

	@Column(name = "img", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '商品图片'")
	private String img;

	@Column(name = "name", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '商品名称'")
	private String name;

	@Column(name = "integral", nullable = false, columnDefinition = "bigint default 0 COMMENT '积分'")
	private int integral;

	@Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-通过 2-不通过'")
	private int status;

	@Column(name = "user_name",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '收货人名称'")
	private String userName;

	@Column(name = "phone",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '电话'")
	private String phone;

	@Column(name = "detailed", columnDefinition = "varchar(512) default '' COMMENT '详细地址'")
	private String detailed;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
	private Date createTime = new Date();
}
