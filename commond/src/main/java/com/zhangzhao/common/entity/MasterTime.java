package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 公共参数
 * 师傅上下班时间
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
public class MasterTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Gotowork", columnDefinition = "DATETIME COMMENT '上班时间'")
	private Date gotoWork;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "getoffwork", columnDefinition = "DATETIME COMMENT '下班时间'")
	private Date getoffWork;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "coupon_start", columnDefinition = "DATETIME COMMENT '优惠券开始时间'")
	private Date couponStart;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "coupon_end", columnDefinition = "DATETIME COMMENT '优惠券结束时间'")
	private Date couponEnd;

	@Column(name = "profile", columnDefinition = "varchar(512) default '' COMMENT '简介内容'")
	private String profile;



	@Column(name = "agreement", columnDefinition = "varchar(512) default '' COMMENT '简介协议'")
	private String agreement;

	@Column(name = "customer", columnDefinition = "varchar(512) default '' COMMENT '客服'")
	private String customer;

	@Column(name = "start_page", columnDefinition = "varchar(512) default ''  COMMENT '启动页'")
	private String startPage;

	@Column(name = "times", columnDefinition = "Decimal(12,2) default 0.00 COMMENT '会员a倍积分'")
	private double times;

}
