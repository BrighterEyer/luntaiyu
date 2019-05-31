package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单详情
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '商品名称'")
    private String name;

    @Column(name = "order_number", columnDefinition = "varchar(512) default '' COMMENT '订单编号'")
    private String orderNumber;

    @Column(name = "img", columnDefinition = "varchar(512) default '' COMMENT '商品图片'")
    private String img;

    @Column(name = "prcie", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '价格'")
    private double price;

    @Column(name = "payment_prcie", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '实际支付价格'")
    private double paymentPrcie;

    @Column(name = "amount",nullable = false, columnDefinition = "int(10)  default 0 COMMENT '数量'")
    private int amount;

    @Column(name = "integral", columnDefinition = "bigint  default 0 COMMENT '积分'")
    private Long integral;

    @Column(name = "fetal_number", columnDefinition = "varchar(512) default '' COMMENT '胎号'")
    private String fetalNumber;

    @Column(name = "property", columnDefinition = "varchar(512) default '' COMMENT '属性'")
    private String property;

    @Column(name = "coupon_id", columnDefinition = "bigint  default 0 COMMENT '优惠券id'")
    private Long couponId;

    @Column(name = "goods_commodity_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '商品id'")
    private Long goodsCommodityId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

    @Column(name = "order_id", columnDefinition = "bigint  default 0 COMMENT '订单id'")
    private Long orderId;

    @Column(name = "evaluate", columnDefinition = "int(10)  default 0 COMMENT '是否已评价 1-已评价'")
    private int evaluate;

    @Column(name = "installationtype", columnDefinition = "int(10)  default 0 COMMENT '安装类型 0-到店安装 1-无需安装 2-上门安装'")
    private String installationType;

    @Column(name = "freightPrcie", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '单个商品运费'")
    private double freightPrcie;
}
