package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 积分记录
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Integral {

    public enum  Type{
        GOODS(1,"商品消费"),EXCHANGE(2,"兑换商品");
        private int type;
        private String desc;
        Type(int type,String desc){
            this.type=type;
            this.desc=desc;
        }
        public int getType(){
            return type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "integration",nullable = false, columnDefinition = "int(10) default 0 COMMENT '积分量'")
    private int integration;

    @Column(name = "type", columnDefinition = "int(1)  default 0 COMMENT '类型 1-商品消费 2-积分兑换'")
    private int type;

    @Column(name = "user_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();
}
