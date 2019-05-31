package com.zhangzhao.common.entity.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 权限
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) default '' COMMENT '权限'")
    private String name;

    @Column(name = "explain_name", nullable = false, columnDefinition = "varchar(255) default '' COMMENT '权限说明'")
    private String explainName;
}
