package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 交易记录
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TradingRecord {

    public enum Status {
        SUCCESSD(1, "成功"), DEFEATED(2, "失败");
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

    public enum Type {
        INCOMES(1, "收入"), EXPEND(2, "支出");
        private int type;
        private String desc;

        Type(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public int getType() {
            return type;
        }

    }

    public enum withholdType {
        OVERTIME(1, "超时扣款"), NEGATIVE_COMMENT(2, "差评扣款");
        private int withholdType;
        private String desc;

        private withholdType(int withholdType, String desc) {
            this.withholdType = withholdType;
            this.desc = desc;
        }

        public int getWithholdType() {
            return withholdType;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false, columnDefinition = "int(1) default 0 COMMENT '类型1-收入2-支出'")
    private int type;

    @Column(name = "withhold_type", columnDefinition = "int(1) default 0 COMMENT '扣款类型1-超时扣款2-差评扣款'")
    private int withholdType;

    @Column(name = "money", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '金额'")
    private double money;

    @Column(name = "user_id", nullable = false, columnDefinition = "bigint default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "cash_account", columnDefinition = "varchar(512) default '' COMMENT '提现账号'")
    private String cashAccount;

    @Column(name = "card_number", columnDefinition = "varchar(512) default '' COMMENT '卡号'")
    private String cardNumber;

    @Column(name = "status", columnDefinition = "int(1) default 0 COMMENT '状态 1-成功2-失败'")
    private int status;

    @Column(name = "instructions", columnDefinition = "varchar(512) default '' COMMENT '扣款说明'")
    private String instructions;

    @Column(name = "evaluate", columnDefinition = "varchar(512) default '' COMMENT '评价分'")
    private String evaluate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

}
