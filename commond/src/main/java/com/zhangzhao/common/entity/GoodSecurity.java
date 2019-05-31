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
 * 商品保障
 * @author Administrator
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class GoodSecurity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '商品保障'")
	private String name;

	@Column(name = "img", columnDefinition = "varchar(512) default '' COMMENT '图片'")
	private String img;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
	private Date createTime;
}
