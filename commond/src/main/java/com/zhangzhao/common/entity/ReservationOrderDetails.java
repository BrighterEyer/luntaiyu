package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 预约订单详情表
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReservationOrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_number", columnDefinition = "bigint default 0 COMMENT '预约单号'")
    private String reservationNumber;

    @Column(name = "province", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '省'")
    private String province;

    @Column(name = "district", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '区'")
    private String district;

    @Column(name = "city", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '市'")
    private String city;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_time", columnDefinition = "DATETIME COMMENT '预约时间'")
    private Date appointmentTime = new Date();

    @Column(name = "phone_number", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '手机号'")
    private String phoneNumber;

    @Column(name = "detailed_address", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '详细地址'")
    private String detailedAddress;

    @Column(name = "user_affirm", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '用户确认'")
    private String userAffirm;

    @Column(name = "master_affirm", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '师傅确认'")
    private String masterAffirm;

    @Column(name = "master_name", nullable = false, columnDefinition = "bigint  default 0 COMMENT '师傅名字'")
    private Long masterName;

    @Column(name = "remark", columnDefinition = "varchar(512) default '' COMMENT '备注'")
    private String remark;

    @Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-待接单 2-待服务 3-服务中 4-待评价 5-已完成'")
    private int status;

    @Column(name = "up_photo", columnDefinition = "varchar(512) default '' COMMENT '用户上半身头像'")
    private String upPhoto;

    @Column(name = "order_money", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '预约费用'")
    private double orderMoney;

    @Column(name = "subsidy_price", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '补贴费'")
    private double subsidyPrice;
}
