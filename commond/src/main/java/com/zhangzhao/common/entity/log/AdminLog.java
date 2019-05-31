package com.zhangzhao.common.entity.log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhangzhao.common.entity.admin.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台日志
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdminLog {

    public enum Type{
        C(1,"增加"),R(2,"查询"),U(3,"更新"),D(4,"删除"),LOGIN(5,"登入");
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

    @Column(name = "type", columnDefinition = "int(1)  default 0 COMMENT '类型 1-增加 2-查询 3-更新 4-删除 5-登入'")
    private int type;

    @Column(name = "content", columnDefinition = "varchar(512) default '' COMMENT '内容'")
    private String content;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY) //  级联保存 更新 删除 刷新 延迟加载
    @JoinColumn(name = "admin_id", columnDefinition = "bigint COMMENT '管理id'",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))   // 外键关联
    private Admin admin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
