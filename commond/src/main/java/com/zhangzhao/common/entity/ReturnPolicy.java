package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 退换货
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
public class ReturnPolicy {

	public enum Status {
		APPLY_REFUND(1, "待商家确认退款/退货退款待确认/待商家确认补发"), PENDING_RETURN(2, "退款已确认/退货退款已确认/补发已确认"),
		ALREADY_COMPLETE(3, "退货退款中/补发收货"),COMPLETE(4, "退货退款已完成");
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

	public enum Type{
		RETURN(1,"退款"),REISSUE(2,"补发"),UNIONPAY(3,"退货退款");
		private int type;
		private String typeName;
		Type(int type,String typeName){
			this.type=type;
			this.typeName=typeName;
		}
		public int getType(){
			return type;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "type", nullable = false, columnDefinition = "int(1)  default 0 COMMENT '类型 1-退款 2-补发 3-退货退款'")
	private int type;

	@Column(name = "why", columnDefinition = "varchar(512) default '' COMMENT '原因'")
	private String why;

	@Column(name = "product_id", columnDefinition = "varchar(512) default '' COMMENT '商品id'")
	private String productId;

	@Column(name = "img", columnDefinition = "varchar(512) default '' COMMENT '图片'")
	private String img;

	@Column(name = "money", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '金额'")
	private double money;

	@Column(name = "instructions", columnDefinition = "varchar(512) default 0 COMMENT '说明'")
	private String instructions;

	@Column(name = "refund_date", columnDefinition = "DATETIME COMMENT '退款时间倒计时'")
	private Date refundDate;

	@Column(name = "company", columnDefinition = "varchar(512) default '' COMMENT '物流公司'")
	private String company;

	@Column(name = "returns_number ", columnDefinition = "varchar(512) default 0 COMMENT '退货物流单号'")
	private String returnsNumber;

	@OneToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id", columnDefinition = "bigint default 0 COMMENT '订单id'",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
	private OrderSupply orderSupply;

	@Column(name = "user_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '用户id'")
	private Long userId;

	@Column(name = "status", columnDefinition = "int(1) default 0 COMMENT '状态 1-待商家确认退款/退货退款待确认/待商家确认补发 2-退款已确认/退货退款已确认/补发已确认 3-退货退款中/补发收货 4-退货退款已完成'")
	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "DATETIME COMMENT '退换货时间'")
	private Date createTime = new Date();

	@Column(name = "returnsName", columnDefinition = "varchar(512) default '' COMMENT '退换名称'")
	private String returnsName;

}
