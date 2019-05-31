package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动列表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Activity {

    public enum Status{
        NORMAL(0,"未使用"),CANCEL(1,"已使用");
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

    public enum  Type{
        MINUS(1,"满减"),RETURN(2,"满返");
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

    @Column(name = "title",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '标题'")
    private String title;

    @Column(name = "img",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '图片'")
    private String img;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false, columnDefinition = "DATETIME COMMENT '开始时间'")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = false, columnDefinition = "DATETIME COMMENT '结束时间'")
    private Date endTime;

    @Column(name = "context", columnDefinition = "text COMMENT '图文详情'")
    private String context;

    @Column(name = "video_url", columnDefinition = "varchar(512) default '' COMMENT '视频'")
    private String videoUrl;

    @Column(name = "return_price",nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '返金额'")
    private double returnPrice;

    @Column(name = "price",nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '金额'")
    private double price;

    @Column(name = "type",nullable = false, columnDefinition = "int(1) default 0 COMMENT '类型 1-满减 2-满返'")
    private int type;

    @Column(name = "status", columnDefinition = "int(2)  default 0 COMMENT '1-已使用'")
    private int status=Status.NORMAL.getStatus();

    @Column(name = "good_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '商品id'")
    private Long goodId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
