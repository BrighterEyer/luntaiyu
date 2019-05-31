package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 投诉
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Complaints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition = "bigint default 0 COMMENT '用户id'",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(name = "cause", columnDefinition = "varchar(512) default '' COMMENT '投诉原因'")
    private String cause;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

}
