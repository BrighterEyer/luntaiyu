package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 预约订单
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {

    public enum Status {
        Waiting_list(1, "待接单"), For_service(2, "待服务"), in_service(3, "服务中"),
        to_be_evaluated(4, "待评价"), have_been_evaluated(5, "已完成");
        private int status;
        private String statusName;

        Status(int status, String statusName) {
            this.status = status;
            this.statusName = statusName;
        }

        public int getStatus() {
            return status;
        }
    }

    public enum StatusOk {
        OK1(1, "用户确认"), OK2(2, "师傅确认"), OK3(3, "双方都确认");

        private int status;
        private String statusName;

        StatusOk(int status, String statusName) {
            this.status = status;
            this.statusName = statusName;
        }

        public int getStatus() {
            return status;
        }
    }

    public enum Type {
        ALIPAY(1, "支付宝"), WECHAT(2, "微信"), UNIONPAY(3, "银联");
        private int type;
        private String typeName;

        private Type(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

    }


    public enum TypeRanking {
        Month(1, "月排名"), Quarterly(2, "季度排名"), year(3, "年排名");
        private int type;
        private String desc;

        TypeRanking(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        private int getType() {
            return type;
        }
    }

    public enum star {
        yixing(1, "1星"), erxing(2, "2星"), sanxing(3, "3星"), sixing(4, "4星"), wuxing(5, "五星");
        private int type;
        private String typeName;

        private star(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

    }

    public enum yesnoShopnote {
        YES(1, "是"), NO(2, "不是");
        private int type;
        private String typeName;

        private yesnoShopnote(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_number", columnDefinition = "bigint default 0 COMMENT '预约编号'")
    private String reservationNumber;

    @Column(name = "type", columnDefinition = "int(10) default 0 COMMENT '支付类型 1-支付宝 2-微信 3-银联'")
    private int type;

    @Column(name = "province", columnDefinition = "varchar(512) default '' COMMENT '省'")
    private String province;

    @Column(name = "district", columnDefinition = "varchar(512) default '' COMMENT '区'")
    private String district;

    @Column(name = "city", columnDefinition = "varchar(512) default '' COMMENT '市'")
    private String city;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_time", columnDefinition = "DATETIME COMMENT '预约时间'")
    private Date appointmentTime;

    @Column(name = "phone_number", nullable = false, columnDefinition = "varchar(512) default '' COMMENT '手机号'")
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "allocate_time", columnDefinition = "DATETIME COMMENT '分配时间'")
    private Date allocateTime = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_time", columnDefinition = "DATETIME COMMENT '完成时间'")
    private Date finishTime = new Date();

    @Column(name = "detailed_address", columnDefinition = "varchar(512) default '' COMMENT '详细地址'")
    private String detailedAddress;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", columnDefinition = "DATETIME COMMENT '时间'")
    private Date time = new Date();

    @Column(name = "user_affirm", nullable = false, columnDefinition = "int(1) default 0 COMMENT '用户确认'")
    private int userAffirm;

    @Column(name = "master_affirm", nullable = false, columnDefinition = "int(1) default 0 COMMENT '师傅确认'")
    private int masterAffirm;

    @Column(name = "user_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '用户id'")
    private Long userId;

    @Column(name = "dismounting", nullable = false, columnDefinition = "int(1) default 1 COMMENT '拆装条数'")
    private int dismounting;

    @Column(name = "positioning", nullable = false, columnDefinition = "int(1) default 0 COMMENT '调位条数'")
    private int positioning;

    @Column(name = "yesno_shopnote", nullable = false, columnDefinition = "int(1) default 0 COMMENT '是否到店单 1-是 2-不是'")
    private int yesnoShopnote;

    @Column(name = "star", nullable = false, columnDefinition = "int(1) default 0 COMMENT '评论星级- 1星 2星 3星 4星 5星'")
    private int star;

    @Column(name = "hao_pinglv", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '好评率'")
    private double haoPinglv;

    @Column(name = "master_id", columnDefinition = "bigint  default 0 COMMENT '师傅id'")
    private Long masterId;

    @Column(name = "remark", columnDefinition = "varchar(512) default '' COMMENT '备注'")
    private String remark;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id", columnDefinition = "bigint default 0 COMMENT '门店id'", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Store store;

    @Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-待接单 2-待服务 3-服务中 4-待评价 5-已完成'")
    private int status;

    @Column(name = "statusOk", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-用户确认 2-师傅确认 3-双方确认'")
    private int statusOk;

    @Column(name = "order_money", nullable = false, columnDefinition = "Decimal(12,2) default 0.0 COMMENT '预约费用'")
    private double orderMoney;

    @Column(name = "subsidy_price", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '补贴费'")
    private double subsidyPrice;

    @Column(name = "income_price", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '师傅收入'")
    private double incomePrice;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "anonymity_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private PrecontractEvaluate precontractEvaluate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "reservation_number", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private ReservationOrderDetails reservationOrderDetails;
}
