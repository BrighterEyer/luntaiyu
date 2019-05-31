package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 服务
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Services {

    public enum Type {
        SHOP(1, "到店");
        private int type;
        private String desc;

        Type(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        private int getType() {
            return type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(255) default '' COMMENT '名称'")
    private String name;

    @Column(name = "type", nullable = false, columnDefinition = "int(1) default 0 COMMENT '类型 0-上门 1-到店'")
    private int type;

    @Column(name = "service_price", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '服务费用'")
    private double servicePrice;

    @Column(name = "subsidy_price", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '补贴费'")
    private double subsidyPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

}
