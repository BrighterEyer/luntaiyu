package com.zhangzhao.common.entity.log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品出入库日志
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "type", columnDefinition = "int(10)  default 0 COMMENT '类型'")
    private int type;

    @Column(name = "stock", columnDefinition = "int(10)  default 0 COMMENT '库存'")
    private int stock;

    @Column(name = "amount", columnDefinition = "int(10)  default 0 COMMENT '数量'")
    private int amount;

    @Column(name = "cause", columnDefinition = "varchar(512) default '' COMMENT '原因'")
    private String cause;

    @Column(name = "good_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '商品id'")
    private Long goodId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
