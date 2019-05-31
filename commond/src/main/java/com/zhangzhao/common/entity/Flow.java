package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 流程
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "procedures", columnDefinition = "varchar(512) default '' COMMENT '跟进流程'")
    private String procedures;

    @Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-待跟进 2-处理中 3-已处理'")
    private int status;

    @Column(name = "cause", columnDefinition = "varchar(512) default '' COMMENT '原因'")
    private String cause;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();
}
