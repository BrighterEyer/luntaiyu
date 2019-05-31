package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 银行卡
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankCard {

    public enum Type {
        SAVINGS(1, "储蓄"), CREDIT(2, "信用");
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

    public enum Status {
        NORMAL(1, "正常"), CANCEL(2, "取消");
        private int status;
        private String desc;

        Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public int getStatus() {
            return status;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "card_number", nullable = false, columnDefinition = "varchar(20) default '' COMMENT '卡号'")
    private String cardNumber;

    @Column(name = "card_issuing_bank", nullable = false, columnDefinition = "varchar(50) default '' COMMENT '发卡银行'")
    private String cardIssuingBank;

    @Column(name = "bank_name", nullable = false, columnDefinition = "varchar(50) default '' COMMENT '持卡人'")
    private String bankName;

    @Column(name = "phone", nullable = false, columnDefinition = "varchar(255) default '' COMMENT '手机号码'")
    private String phone;

    @Column(name = "type", nullable = false, columnDefinition = "int(5) default 0 COMMENT '类型 1-储蓄 2-信用'")
    private int type;

    @Column(name = "status", nullable = false, columnDefinition = "int(10) default 0 COMMENT '状态 1-正常 2-取消'")
    private int status = Status.NORMAL.getStatus();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY) //  级联保存 更新 删除 刷新 延迟加载
    @JoinColumn(name = "id", columnDefinition = "int(100) COMMENT '用户外键'",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))   // 外键关联
    private User user;


}
