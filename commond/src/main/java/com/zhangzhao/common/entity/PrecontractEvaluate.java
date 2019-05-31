package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 预约评价
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PrecontractEvaluate {

    public enum Status {
        anonymity(1, "匿名"), noanonymity(2, "不匿名");
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

    @Column(name = "content", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '内容'")
    private String content;

    @Column(name = "img", columnDefinition = "varchar(512) default '' COMMENT '图片'")
    private String img;

    @Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-匿名 2-不匿名'")
    private int status;

    @Column(name = "user_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "type", columnDefinition = "int(10) default 0 COMMENT '星级类型 1-星 2-星 3-星 4-星 5-星'")
    private int type;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '预约评论时间'")
    private Date createTime = new Date();

}
