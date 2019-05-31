package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 热门搜索词
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region", columnDefinition = "int(10) default 0 COMMENT '城市记录'")
    private int region;

    @Column(name = "city", columnDefinition = "varchar(512) default '' COMMENT '城市'")
    private String city;

    @Column(name = "keyword", columnDefinition = "varchar(512) default '' COMMENT '热门词汇'")
    private String keyword;

    @Column(name = "cout", columnDefinition = "int(10) default 0 COMMENT '热门词汇记录'")
    private int cout;

    @Column(name = "cityId", columnDefinition = "int(10) default '0' COMMENT '城市Id'")
    private Long cityId;

}
