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
 * 用户
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    /**
     * 用户类型
     */
    public enum Type {
        USER(1, "用户"), MASTER(2, "师傅");
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

    /**
     * 会员类型
     */
    public enum member {
        common(0, "普通"), member(1, "会员"), cooperate(2, "搭档");
        private int type;
        private String typeName;

        member(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

        public int getType() {
            return type;
        }
    }

    /**
     * 用户账号状态
     */
    public enum Status {
        NORMAL(1, "正常"), BAN(2, "封号");
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

    /**
     * 用户申请师傅状态
     */
    public enum ApplyStatus {
        apply(0, "申请"), YES(1, "通过"), NO(2, "不通过");
        private int applyStatus;
        private String statusName;

        ApplyStatus(int applyStatus, String statusName) {
            this.applyStatus = applyStatus;
            this.statusName = statusName;
        }

        public int getApplyStatus() {
            return applyStatus;
        }
    }


    public enum Use {
        no(0, "默认没使用"), yes(1, "使用中");
        private int type;
        private String typeName;

        Use(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

        public int getType() {
            return type;
        }
    }


    /**
     * 师傅类型
     */
    public enum TypeMaster {
        SHOP(1, "轮胎店"), PREGNANCY(2, "补胎"), REPAIRFACTORY(3, "汽修厂"), FIREREPAIR(4, "火补胎");
        private int typeMaster;
        private String statusName;

        TypeMaster(int typeMaster, String statusName) {
            this.typeMaster = typeMaster;
            this.statusName = statusName;
        }

        public int getTypeMaster() {
            return typeMaster;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "user_name", columnDefinition = "varchar(512) default '' COMMENT '用户名'")
    //private String userName;

    @Column(name = "password", columnDefinition = "varchar(512) default '' COMMENT '密码'")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_time", columnDefinition = "DATETIME COMMENT '注册时间'")
    private Date registerTime = new Date();

    @Column(name = "status", columnDefinition = "int(10) default 1 COMMENT '账号状态1-正常 2-封号'")
    private int status;

    @Column(name = "phone", columnDefinition = "varchar(512) default '' COMMENT '手机号'")
    private String phone;

    @Column(name = "payment_password", columnDefinition = "varchar(512) default '' COMMENT '支付密码'")
    private String paymentPassword;

    @Column(name = "identity_front", columnDefinition = "varchar(512) default '' COMMENT '身份证正面'")
    private String identityFront;

    @Column(name = "identity_reverse", columnDefinition = "varchar(512) default '' COMMENT '身份证反面'")
    private String identityReverse;

    @Column(name = "video", columnDefinition = "varchar(512) default '' COMMENT '视频'")
    private String video;

    @Column(name = "rests", columnDefinition = "varchar(512) default '' COMMENT '其他'")
    private String rests;

    @Column(name = "up_photo", columnDefinition = "varchar(512) default '' COMMENT '用户上半身头像'")
    private String upPhoto;

    @Column(name = "apply_status", columnDefinition = "int(1) default 0 COMMENT '师傅申请状态 0- 申请 1-通过 2-不通过'")
    private int applyStatus;

    @Column(name = "type", nullable = false, columnDefinition = "int(5) default 0 COMMENT '类型 1-用户 2-师傅'")
    private int type;

    @Column(name = "user_model", columnDefinition = "int(10)  default 0 COMMENT  '用户模块'")
    private int userModel;

    @Column(name = "master_model", columnDefinition = "int(10) default 0 COMMENT  '师傅模块'")
    private int masterModel;

    @Column(name = "use_type", columnDefinition = "int(2) default 0 COMMENT '使用'")
    private Use use = Use.no;

    @Column(name = "grade", columnDefinition = "varchar(512) default '' COMMENT '等级'")
    private String grade;

    @Column(name = "id_card", columnDefinition = "varchar(20) default '' COMMENT '身份证号码'")
    private String idCard;

    @Column(name = "gender", columnDefinition = "varchar(512) default '' COMMENT '性别 0-男 1-女'")
    private String gender;

    @Column(name = "name", columnDefinition = "varchar(255) default '' COMMENT '昵称'")
    private String name;

    @Column(name = "real_name", columnDefinition = "varchar(255) default '' COMMENT '实名'")
    private String reaName;

    @Column(name = "icon", columnDefinition = "varchar(512) default '' COMMENT '用户头像'")
    private String icon;

    @Column(name = "microblog", columnDefinition = "varchar(512) default '' COMMENT '微博'")
    private String microblog;

    @Column(name = "QQ", columnDefinition = "varchar(512) default '' COMMENT 'QQ'")
    private String qq;

    @Column(name = "wechat", columnDefinition = "varchar(512) default '' COMMENT '微信'")
    private String wechat;

    @Column(name = "road_condition", columnDefinition = "varchar(512) default '' COMMENT '路况'")
    private String roadCondition;

    @Column(name = "vehicle_weight", columnDefinition = "varchar(512) default '' COMMENT '车重'")
    private String vehicleWeight;

    @Column(name = "vehicle", columnDefinition = "varchar(512) default '' COMMENT '车型'")
    private String vehicle;

    @Column(name = "remark", columnDefinition = "varchar(512) default '' COMMENT '备注'")
    private String remark;

    @Column(name = "type_service", columnDefinition = "int(1) default 0 COMMENT '服务类型'")
    private int typeService;

    @Column(name = "type_master", columnDefinition = "int(1) default 0 COMMENT '师傅类型'")
    private int typeMaster;

    @Column(name = "detailed_address", columnDefinition = "varchar(512) default '' COMMENT '详细地址'")
    private String detailedAddress;

    @Column(name = "province", columnDefinition = "varchar(512) default '' COMMENT '省'")
    private String province;

    @Column(name = "district", columnDefinition = "varchar(512) default '' COMMENT '区'")
    private String district;

    @Column(name = "city", columnDefinition = "varchar(512) default '' COMMENT '市'")
    private String city;

    @Column(name = "member", columnDefinition = "int(2) default 0 COMMENT '会员类型 0-普通 1-会员 2-搭档'")
    private int member;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "membership_time", columnDefinition = "DATETIME COMMENT '会员时间'")
    private Date membershipTime;

    @Column(name = "amount", columnDefinition = "int(10)  default 0 COMMENT '数量'")
    private int amount;

    @Column(name = "gratis_time", columnDefinition = "int(10)  default 0 COMMENT '免费次数'")
    private int gratisTime;

    @Column(name = "receipts", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '实收'")
    private double receipts;

    @Column(name = "integral", nullable = false, columnDefinition = "int(10) default 0 COMMENT '积分量'")
    private int integral;

    @Column(name = "order_money", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '预约费用'")
    private double orderMoney;

    @Column(name = "overtime_money", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '超时扣款金额'")
    private double overtimeMoney;

    @Column(name = "negative_money", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '差评扣款金额'")
    private double negativeMoney;

    @Column(name = "good_money", columnDefinition = "Decimal(12,2) default 0.0 COMMENT '好评打赏金额'")
    private double goodMoney;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timeout_period", columnDefinition = "DATETIME COMMENT '超时时间'")
    private Date timeoutPeriod;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();

    /**
     * 地址
     */
    @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<Address> addresses;

    /**
     * 积分记录
     */
    @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<Integral> integrals;
    /**
     * 交易记录
     */
    @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<TradingRecord> transactionRecords;

    /**
     * 优惠券
     */
    @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<Coupon> coupons;

    /**
     * 优惠累积数量
     */
    @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<UserGoods> userGoods;

    /**
     * 发票
     */
    @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<Invoice> invoices;

    /**
     * 用户搭档
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "user_id", columnDefinition = "bigint default 0 COMMENT '用户id'", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private UserCooperate userCooperate;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "user_id", columnDefinition = "Long default 0 COMMENT '用户外键'", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Wallet wallet;
}
