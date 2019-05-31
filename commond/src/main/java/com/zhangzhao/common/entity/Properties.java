package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 商品属性
 * @author Administrator
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Properties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '属性名'")
	private String name;

	@Column(name = "val", columnDefinition = "varchar(512) default '' COMMENT '属性值'")
	private String val;

	@Column(name = "parent_id", columnDefinition = "bigint  default 0 COMMENT '父id'")
	private Long parentId;

	@Transient
	private List<Properties> properties=Lists.newArrayList();
}
