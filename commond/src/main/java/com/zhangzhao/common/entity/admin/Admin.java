package com.zhangzhao.common.entity.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 管理员
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Admin {

    public enum Status{
        ENABLE(1,"启用"),FROZEN(2,"冻结");
        private int status;
        private String statusName;
        Status(int status,String statusName){
            this.status=status;
            this.statusName=statusName;
        }
        public int getStatus(){
            return status;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "icon", columnDefinition = "varchar(512) default '' COMMENT '用户头像'")
    private String icon;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) default '' COMMENT '名称'")
    private String name;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '密码'")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

    @Column(name = "status", nullable = false, columnDefinition = "int(1) COMMENT '状态 1-启用 2-冻结'")
    private int status = Status.ENABLE.getStatus();

    @Column(name = "parent_id", columnDefinition = "bigint default 0 COMMENT '父类'")
    private Long parentId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //  级联保存 更新 删除 刷新 延迟加载
    @JoinColumn(name = "authority_id", columnDefinition = "bigint COMMENT '权限'")   // 外键关联
    private List<Authority> authority;

}
