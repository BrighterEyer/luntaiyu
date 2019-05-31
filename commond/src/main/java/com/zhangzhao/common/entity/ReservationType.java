package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 预约类型
 *
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReservationType {

    public enum Reservationtype {
        YES(1, "预约"), NO(2, "到店");
        private int type;
        private String typeName;

        private Reservationtype(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Reservation_type", columnDefinition = "varchar(512) default '' COMMENT '预约类型'")
    private String ReservationType;

    @Column(name = "yesno_shopnote", nullable = false, columnDefinition = "int(1) default 0 COMMENT '预约类型 1-预约 2-到店'")
    private int yesnoShopnote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime = new Date();


}
