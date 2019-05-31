package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 发票
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Invoice {

    public enum Type{
        PERSONAL(1,"个人"),ENTERPRISE(2,"企业");
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

    @Column(name = "rise", columnDefinition = "varchar(512) default '' COMMENT '抬头'")
    private String rise;

    @Column(name = "name",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "duty_paragraph", columnDefinition = "varchar(512) default '' COMMENT '税号'")
    private String dutyParagraph;

    @Column(name = "type",nullable = false, columnDefinition = "int(10)  default 0 COMMENT '类型 1-个人 2-企业'")
    private int type=Type.PERSONAL.getType();

    @Column(name = "used", columnDefinition = "int(2)  default 0 COMMENT '1-默认'")
    private int used;

    @Column(name = "user_id",nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime=new Date();
}
