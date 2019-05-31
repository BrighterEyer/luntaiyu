package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 物流
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Logistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "numbers",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '单号'")
    private String numbers;

    @Column(name = "distributor", columnDefinition = "varchar(512) default '' COMMENT '配送员'")
    private String distributor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

}
