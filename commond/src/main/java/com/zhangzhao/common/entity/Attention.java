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
 * 关注
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
public class Attention {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '用户id'")
	private Long userId;

	@Column(name = "product_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '商品id'")
	private Long productId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
	private Date createTime;
}
