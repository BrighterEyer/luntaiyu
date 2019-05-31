package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 会员价格
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {

    public enum TimeLimit{
        ONE(1, "1个月"), THREE(2, "3个月"), SIX(3, "6个月"),TWELVE(4, "12个月");
        private int status;
        private String statusName;
        TimeLimit(int status, String statusName) {
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

    @Column(name = "member_prcie", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '会员价格'")
    private double memberPrcie;

    @Column(name = "instructions", columnDefinition = "text COMMENT '说明'")
    private String instructions;

    @Column(name = "time_limit", nullable = false, columnDefinition = "int(10) default 0 COMMENT '会员时限 1-1月 2-3月 3-6月 4-12月'")
    private int timeLimit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
