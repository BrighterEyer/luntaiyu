package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 提现
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
public class Withdraw {

	public enum Status {
		REQUEST(1, "申请"), SUCCESSD(2, "成功"), DEFEATED(3, "失败");
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

	public enum Type {
		ALIPAY(1, "支付宝"), WECHAT(2, "微信"), UNIONPAY(3, "银联");
		private int type;
		private String typeName;
		Type(int type, String typeName) {
			this.type = type;
			this.typeName = typeName;
		}
		public int getType() {
			return type;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "money", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '提现金额'")
	private double money;

	@Column(name = "type", columnDefinition = "int(10) default 0 COMMENT '提现方式 1-支付宝 2-微信 3-银联'")
	private int type;

	@Column(name = "cash_account", columnDefinition = "varchar(512) default '' COMMENT '提现账号'")
	private String cashAccount;

	@Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-申请 2-成功 3-失败'")
	private int status;

	@Column(name = "user_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
	private Long userId;

	@Column(name = "card_number", columnDefinition = "varchar(512) default '' COMMENT '卡号'")
	private String cardNumber;

	@Column(name = "bank", columnDefinition = "varchar(512) default '' COMMENT '银行'")
	private String bank;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
	private Date createTime = new Date();
}
