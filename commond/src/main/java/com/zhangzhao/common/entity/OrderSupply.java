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
 * 订单
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderSupply {

    public enum Status{
        NO(-2,"取消"),DEL(-1,"已删除"),PENDING_PAYMENT(1,"待付款"),PENDING_DELIVERY(2,"待发货"),ALREADY_SHIPPED(3,"待收货"),
        TO_BE_EVALUATED(4,"待评价"),HAVE_BEEN_EVALUATED(5,"已完成"),REFUND(6,"已退款"),REFUNDMENT(7,"已退货退款"),
        REISSUE(8,"已补发"),APPLY(9,"待商家确认退款"),APPLY_REFUND(10, "退货退款待确认"), PENDING_RETURN(11, "退货退款已确认"),
        ALREADY_COMPLETE(12, "退货退款中"),COMPLETE(13, "待商家确认补发"),AREISSUE(14,"补发已发货"),BREISSUE(15,"质量审核");
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

    public enum Type{
        ALIPAY(1,"支付宝"),WECHAT(2,"微信"),UNIONPAY(3,"银联");
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
    public enum RedPacket{
        NO(0,"未发送"),YES(1,"已发送");
        private int redPacket;
        private String typeName;
        RedPacket(int type,String typeName){
            this.redPacket=type;
            this.typeName=typeName;
        }
        public int getRedPacket(){
            return redPacket;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", columnDefinition = "varchar(512) default '' COMMENT '订单编号'")
    private String orderNumber;

    @Column(name = "order_prcie", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '订单总价格'")
    private double orderPrcie;

    @Column(name = "good_prcie", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '商品总价格'")
    private double goodPrcie;

    @Column(name = "payment_prcie", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '实际支付价格'")
    private double paymentPrcie;

    @Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 -2取消 -1-删除 1-待付款 2-待发货 3-待收货 4-待评价 5-已完成 6-退款 " +
            "7-退货退款'")
    private int status;

    @Column(name = "payment_type", columnDefinition = "int(10) default 0 COMMENT '支付方式 1-支付宝 2-微信 3-银联'")
    private int paymentType;

    @Column(name = "distribution", columnDefinition = "int(10) default 0 COMMENT '配送方式'")
    private int distribution;

    @Column(name = "reissue_logistics", columnDefinition = "varchar(512) default '' COMMENT '补发物流公司'")
    private String reissueLogistics;

    @Column(name = "reissue", columnDefinition = "varchar(512) default '' COMMENT '补发物流'")
    private String reissue;

    @Column(name = "company_logistics", columnDefinition = "varchar(512) default '' COMMENT '物流公司'")
    private String companyLogistics;

    @Column(name = "logistics", columnDefinition = "varchar(512) default '' COMMENT '物流单号'")
    private String logistics;

    @Column(name = "install_prcie", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '安装费'")
    private double installPrcie;

    @Column(name = "freight_prcie", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '运费'")
    private double freightPrcie;

    @Column(name = "remark", columnDefinition = "varchar(512) default '' COMMENT '备注'")
    private String remark;

    @Column(name = "integral", columnDefinition = "int(10)  default 0 COMMENT '积分'")
    private int integral;

    @Column(name = "type", columnDefinition = "int(10) default 0 COMMENT '类型 1-不参与优惠 2-参与优惠'")
    private int type;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private List<OrderDetails> orderDetails;

    @Column(name = "address_id", columnDefinition = "bigint default 0 COMMENT '收货id'")
    private Long addressId;

    @Column(name = "name",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '名称'")
    private String name;

    @Column(name = "phone",nullable = false, columnDefinition = "varchar(512) default '' COMMENT '电话'")
    private String phone;

    @Column(name = "province", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '省'")
    private String province;

    @Column(name = "province_id", nullable = false, columnDefinition = "bigint COMMENT '省ID'")
    private Long provinceId;

    @Column(name = "city", columnDefinition = "varchar(512) default '' COMMENT '市'")
    private String city;

    @Column(name = "city_id", columnDefinition = "bigint default 0 COMMENT '市ID'")
    private Long cityId;

    @Column(name = "district", columnDefinition = "varchar(512) default '' COMMENT '区'")
    private String district;

    @Column(name = "district_id", columnDefinition = "bigint default 0 COMMENT '区ID'")
    private Long districtId;

    @Column(name = "detailed", columnDefinition = "varchar(512) default '' COMMENT '详细地址'")
    private String detailed;

    @Column(name = "red_packet", columnDefinition = "int(10) default 0 COMMENT '类型 0-未发送 1-已发送'")
    private int redPacket;

    @Column(name = "reservations", columnDefinition = "varchar(512) default '' COMMENT '预约id'")
    private String reservations;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition = "bigint default 0 COMMENT '用户id'",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(name = "invoice_id", columnDefinition = "bigint  default 0 COMMENT '发票id'")
    private Long invoiceId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pre_installation_time", columnDefinition = "DATETIME COMMENT '预安装时间'")
    private Date preInstallationTime;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id", columnDefinition = "bigint default 0 COMMENT '门店id'",foreignKey = @ForeignKey(name =
            "none",value = ConstraintMode.NO_CONSTRAINT))
    private Store store;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "store_installation_time", columnDefinition = "DATETIME COMMENT '上门安装时间'")
    private Date storeInstallationTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "complete_time", columnDefinition = "DATETIME COMMENT '完成时间'")
    private Date completeTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

}
