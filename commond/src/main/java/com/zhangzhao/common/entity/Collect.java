package com.zhangzhao.common.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 收藏
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
public class Collect {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '用户id'")
	private Long userId;

//	@Column(name = "product_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '商品id'")
//	private Long productId;
	@ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", referencedColumnName = "id", columnDefinition = "bigint default 0 COMMENT '商品id'",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
	private GoodsCommodity goodsCommodity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
	private Date createTime = new Date();
}
