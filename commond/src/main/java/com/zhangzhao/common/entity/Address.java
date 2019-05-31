package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 地址
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "phone",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '电话'")
    private String phone;

    @Column(name = "province", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '省'")
    private String province;

    @Column(name = "province_id", columnDefinition = "bigint COMMENT '省ID'")
    private Long provinceId;

    @Column(name = "city", columnDefinition = "varchar(512) default '' COMMENT '市'")
    private String city;

    @Column(name = "city_id", columnDefinition = "bigint COMMENT '市ID'")
    private Long cityId;

    @Column(name = "district", columnDefinition = "varchar(512) default '' COMMENT '区'")
    private String district;

    @Column(name = "district_id", columnDefinition = "bigint COMMENT '区ID'")
    private Long districtId;

    @Column(name = "longitude", columnDefinition = "Decimal(12,6) default 0.0 COMMENT '经度'", scale = 6, precision = 12)
    private double longitude;

    @Column(name = "latitude", columnDefinition = "Decimal(12,6) default 0.0 COMMENT '纬度'", scale = 6, precision = 12)
    private double latitude;

    @Column(name = "detailed", columnDefinition = "varchar(512) default '' COMMENT '详细地址'")
    private String detailed;

    @Column(name = "used", columnDefinition = "int(2)  default 0 COMMENT '1-默认'")
    private int used;

    @Column(name = "user_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime=new Date();
}
