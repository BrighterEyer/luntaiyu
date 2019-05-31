package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 购物车
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "img",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '图片'")
    private String img;

    @Column(name = "price",nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '价格'")
    private double price;

    @Column(name = "amount",nullable = false, columnDefinition = "int(10)  default 0 COMMENT '数量'")
    private int amount;

    @Column(name = "property",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '属性'")
    private String property;

    @Column(name = "integral", nullable = false, columnDefinition = "bigint default 0 COMMENT '积分'")
    private int integral;

    @Column(name = "user_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "good_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '商品id'")
    private Long goodId;

    @Column(name = "installationtype", columnDefinition = "varchar(512) default '' COMMENT '安装类型 0-到店安装 1-无需安装 2-上门安装'")
    private String installationType;

    @Column(name = "type", columnDefinition = "int(5) default 0 COMMENT '类型 0-普通 1-优质'")
    private int type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();
}
