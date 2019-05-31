package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 礼品信息
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
public class Present {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "img", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '图片'")
    private String img;

    @Column(name = "context", columnDefinition = "text COMMENT '内容'")
    private String context;

    @Column(name = "integral", columnDefinition = "bigint default 0 COMMENT '积分'")
    private int integral;

    @Column(name = "inventory", columnDefinition = "int default 0 COMMENT '库存'")
    private int inventory;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
