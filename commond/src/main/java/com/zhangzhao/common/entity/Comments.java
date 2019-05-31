package com.zhangzhao.common.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 评论
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
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "goods_commodity_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '商品id'")
	private Long goodsCommodityId;

	@Column(name = "commcontent", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '评论内容'")
	private String commcontent;

	@Column(name = "imgs", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '评论图片'")
	private String imgs;

	@Column(name = "type", nullable = false, columnDefinition = "int(10) default 0 COMMENT '评价类型 1-5星'")
	private int type;

	@ManyToOne(cascade = {CascadeType.DETACH},fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",referencedColumnName = "id", columnDefinition = "bigint default 0 COMMENT '用户id'",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
	private User user;

	@Column(name = "fucomments_id", columnDefinition = "bigint default 0 COMMENT '评论父id'")
	private Long fuCommentsId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "DATETIME COMMENT '评论时间'")
	private Date createTime = new Date();

}
