package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 我的钱包
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wallet {

    public enum Status {
        THROUGH(1, "通过"), NOT_THROUGH(2, "不通过");
        private int status;
        private String statusName;

        Status(int status, String statusName) {
            this.status = status;
            this.statusName = statusName;
        }

        public int getStatus() {
            return status;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "balance", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '余额'")
    private double balance;

    @Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-通过 2-不通过'")
    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

}
