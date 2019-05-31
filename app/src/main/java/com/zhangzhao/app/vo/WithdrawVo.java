package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Accessors(chain = true)
public class WithdrawVo {
    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "提现金额")
    private String money;

    @ApiModelProperty(value = "提现方式 1-支付宝 2-微信 3-银联")
    private int type;

    @ApiModelProperty(value = "提现支付宝账号")
    private String cashAccount;

    @ApiModelProperty(value = "状态 1-申请 2-成功 3-失败")
    private int status;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "卡号")
    private String cardNumber;

    @ApiModelProperty(value = "银行")
    private String bank;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "提现时间")
    private Date createTime;

}
