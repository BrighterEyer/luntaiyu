package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 质量处理
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quality {

    public enum Status {
        FOLLOW(1, "待跟进"), PROCESS(2, "处理中"), PROCESSED(3, "已处理");
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

    @Column(name = "user_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "cause", columnDefinition = "varchar(512) default '' COMMENT '原因'")
    private String cause;

    @Column(name = "img", columnDefinition = "varchar(512) default '' COMMENT '图片'")
    private String img;

    @Column(name = "explains", columnDefinition = "varchar(512) default '' COMMENT '说明'")
    private String explains;

    @Column(name = "status", nullable = false, columnDefinition = "int(1) default 1 COMMENT '状态 1-待跟进 2-处理中 3-已处理'")
    private int status;

    @Column(name = "report", columnDefinition = "varchar(512) default '' COMMENT '报告'")
    private String report;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();
}
