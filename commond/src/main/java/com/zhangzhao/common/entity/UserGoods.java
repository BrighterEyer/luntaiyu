package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户商品数量累积
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserGoods {

    @Id
    @Column(name = "user_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "amount", columnDefinition = "int(10)  default 0 COMMENT '数量'")
    private int amount;

    @Column(name = "good_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '商品id'")
    private Long goodId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
