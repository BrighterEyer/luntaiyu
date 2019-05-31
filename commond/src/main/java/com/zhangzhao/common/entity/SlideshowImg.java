package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 轮播图
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SlideshowImg {

    public enum Type{
        MAIN(1,"首页"),ACTIVITY(2,"活动");
        private int type;
        private String typeName;
        Type(int type,String typeName){
            this.type=type;
            this.typeName=typeName;
        }
        public int getType(){
            return type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img_url",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '图片地址'")
    private String imgUrl;

    @Column(name = "img_jump",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '跳转地址'")
    private String imgJump;

    @Column(name = "type",nullable = false, columnDefinition = "int(5) default 0 COMMENT '类型 1-首页 2-活动'")
    private int type;

    @Column(name = "name", columnDefinition = "varchar(512)  default '' COMMENT '名称'")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();
}
