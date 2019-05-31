package com.zhangzhao.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 出库表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OutDelivery {

    public enum DeliveryDetail {
        ORDINARY(1, "平台出库"), QUALITY(2, "手动出库");
        private int deliveryDetail;
        private String desc;

        DeliveryDetail(int deliveryDetail, String desc) {
            this.deliveryDetail = deliveryDetail;
            this.desc = desc;
        }

        private int getDeliveryDetail() {
            return deliveryDetail;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tyre_number", columnDefinition = "varchar(512) default '' COMMENT '轮胎编号'")
    private String tyreNumber;

    @Column(name = "delivery_count", columnDefinition = "int default 0 COMMENT '出库数量'")
    private int deliveryCount;

    @Column(name = "delivery_cause", columnDefinition = "varchar(512) default '' COMMENT '出库原因'")
    private String deliveryCause;

    @Column(name = "delivery_detail", columnDefinition = "int default 0 COMMENT '出库明细 1 -平台 2 -手动'")
    private int deliveryDetail;

    @Column(name = "good_id", nullable = false, columnDefinition = "bigint  default 0 COMMENT '指定商品ID'")
    private Long goodId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
