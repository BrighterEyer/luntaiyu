/**
 * Copyright (C), 2018
 * FileName: Ranking
 * Author:   Administrator
 * Date:     2018/10/31 10:16
 * Description:
 */
package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 〈排名〉
 *
 * @author Administrator
 * @create 2018/10/31
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ranking {

    public enum Type {
        month(1, "月"), quarter(2, "季度"), year(3, "年");
        private int type;
        private String typeName;

        Type(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

        public int getType() {
            return type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "icon", columnDefinition = "varchar(512) default '' COMMENT '用户头像'")
    private String icon;

    @Column(name = "name", columnDefinition = "varchar(255) default '' COMMENT '昵称'")
    private String name;

    @Column(name = "haoping_lv", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '好评率'")
    private double haoPinglv;

    @Column(name = "type", columnDefinition = "int(5) default 0 COMMENT '类型 1月 2季度 3年'")
    private int type;
}
