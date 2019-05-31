package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 优惠券
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Coupon {

    public enum Type {
        COUPON(1, "优惠券"), VOUCHER(2, "代金券");
        private int type;
        private String typeName;

        Type(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

        public int getType() {
            return type;
        }
    }

    public enum Status {
        NORMAL(0, "未使用"), CANCEL(1, "已使用"), pastdue(2, "已过期");
        private int status;
        private String desc;

        Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        private int getStatus() {
            return status;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "type", nullable = false, columnDefinition = "int(10)  default 0 COMMENT '类型 1-优惠券 2-代金券'")
    private int type;

    @Column(name = "price", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '金额'")
    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = false, columnDefinition = "DATETIME COMMENT '到期时间'")
    private Date endTime;

    @Column(name = "good_id", columnDefinition = "varchar(512) default '' COMMENT '指定商品id'")
    private String goodId;

    @Column(name = "status", columnDefinition = "int(2)  default 0 COMMENT '0-未使用 1-已使用 2-已过期'")
    private int status;

    @Column(name = "user_id", columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();
}
