package com.zhangzhao.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhangzhao.common.entity.User.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 打卡
 * 
 * @author Administrator
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PunchCard {

	public enum Status {
		success(1, "成功"), defeated(2, "失败");
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "master_id", columnDefinition = "bigint  default 0 COMMENT '师傅ID'")
	private Long masterId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "card_time", columnDefinition = "DATETIME COMMENT '打卡时间'")
	private Date cardTime;

	@Column(name = "status", nullable = false, columnDefinition = "int(1) default 0 COMMENT '状态 1-成功 2-失败'")
	private int status;
}
