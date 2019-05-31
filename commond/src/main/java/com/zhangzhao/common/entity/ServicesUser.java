package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 服务发放
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ServicesUser {

    public enum Status{
        NO(0,"未用完"),YES(1,"已用完");
        private int status;
        private String desc;
        Status(int status,String desc){
            this.status=status;
            this.desc=desc;
        }
        private int getStatus(){
            return status;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false, columnDefinition = "int(10) default 0 COMMENT '状态 1-已用完'")
    private int status = Status.NO.getStatus();

    @Column(name = "user_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "services_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '服务id'")
    private Long servicesId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

}
