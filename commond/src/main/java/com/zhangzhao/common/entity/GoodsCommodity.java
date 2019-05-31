package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 商品信息
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GoodsCommodity {

    public enum InstallationType {
        SHOP("0", "无需安装"), NO("1", "安装");
        private String type;
        private String desc;

        InstallationType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        private String getType() {
            return type;
        }
    }


    public enum Type {
        ORDINARY(0, "普通胎"), QUALITY(1, "优质胎");
        private int type;
        private String desc;

        Type(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        private int getType() {
            return type;
        }
    }

    public enum Status {
        NORMAL(0, "正常"), CANCEL(1, "删除");
        private int status;
        private String desc;

        Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public int getStatus() {
            return status;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '商品名称'")
    private String name;

    @Column(name = "vice_name", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '商品副名称'")
    private String viceName;

    @Column(name = "img", columnDefinition = "varchar(512) default '' COMMENT '商品主图片'")
    private String img;

    @Column(name = "price", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '商品价格'")
    private double price;

    @Column(name = "cooperate_prcie", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '搭档x价格'")
    private double cooperatePrcie;

    @Column(name = "property", columnDefinition = "varchar(512) default '' COMMENT '属性'")
    private String property;

    @Column(name = "member_Y_prcie", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '会员Y价格'")
    private double memberYprcie;

    @Column(name = "member_X_prcie", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '会员X价格'")
    private double memberXprcie;

    @Column(name = "freight_prcie", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '运费单价'")
    private double freightPrcie;

    @Column(name = "noyes_hotsell", columnDefinition = "varchar(512) default '0' COMMENT '是否热销 1-热销'")
    private String noyesHotsell;

    @Column(name = "free_shipping", columnDefinition = "int(5) default 0 COMMENT '满x免运费'")
    private int freeShipping;

    @Column(name = "choiceness", columnDefinition = "varchar(512) default '0' COMMENT '精选'")
    private String choiceness;

    @Column(name = "promotion_price", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '促销价'")
    private double promotionPrice;

    @Column(name = "integral", columnDefinition = "bigint default 0 COMMENT '积分'")
    private int integral;

    @Column(name = "discount", columnDefinition = "bigint default 0 COMMENT '优惠 1-不参与优惠'")
    private int discount;

    @Column(name = "one_class_id", columnDefinition = "bigint default 0 COMMENT '一级分类ID'")
    private Long oneClassId;

    @Column(name = "two_class_id", columnDefinition = "bigint default 0 COMMENT '二级分类ID'")
    private Long twoClassId;

    @Column(name = "three_class_id", columnDefinition = "bigint default 0 COMMENT '三级分类ID'")
    private Long threeClassId;

    @Column(name = "inventory", columnDefinition = "int default 0 COMMENT '商品库存'")
    private int inventory;

    @Column(name = "delivery", columnDefinition = "int default 0 COMMENT '出库总数'")
    private int delivery;

    @Column(name = "sales", columnDefinition = "int default 0 COMMENT '销量'")
    private int sales;

    @Column(name = "buyinstructions", columnDefinition = "varchar(512) default '' COMMENT '购买说明'")
    private String buyInstructions;

    @Column(name = "installationtype", columnDefinition = "varchar(512) default '' COMMENT '安装类型 0-无需安装 1-安装'")
    private String installationType;

    @Column(name = "installation_fee", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '安装费'")
    private double installationFee;

    @Column(name = "type", nullable = false, columnDefinition = "int(5) default 0 COMMENT '类型 0-普通 1-优质'")
    private int type = Type.ORDINARY.getType();

    @Column(name = "brand", columnDefinition = "varchar(512) default '' COMMENT '品牌'")
    private String brand;

    @Column(name = "figure", columnDefinition = "varchar(512) default '' COMMENT '花纹'")
    private String figure;

    @Column(name = "series", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '系列值'")
    private String series;

    @Column(name = "goods_imgs", columnDefinition = "varchar(512) default '' COMMENT '轮播图'")
    private String goodsImgs;

    @Column(name = "status", columnDefinition = "int(10) default 0 COMMENT '状态 0-正常 1-删除'")
    private int status;

    @Column(name = "context", columnDefinition = "text COMMENT '图文详情'")
    private String context;

    @Column(name = "model", columnDefinition = "varchar(512) default '' COMMENT '型号'")
    private String model;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

    @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_commodity_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
//评论
    private List<Comments> comments;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GoodSecurity> goodSecurities;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Properties> properties;

}
