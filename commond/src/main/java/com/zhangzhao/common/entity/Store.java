package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 门店
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "phone", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '电话'")
    private String phone;

    @Column(name = "province", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '省'")
    private String province;

    @Column(name = "city", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '市'")
    private String city;

    @Column(name = "district", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '区'")
    private String district;

    @Column(name = "detailed", columnDefinition = "varchar(512) default '' COMMENT '详细地址'")
    private String detailed;

    @Column(name = "longitude", columnDefinition = "varchar(512) default '' COMMENT '经度'")
    private String longitude;

    @Column(name = "latitude", columnDefinition = "varchar(512) default '' COMMENT '纬度'")
    private String latitude;

    @Column(name = "user_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

}
