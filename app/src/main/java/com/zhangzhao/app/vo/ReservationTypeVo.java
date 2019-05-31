package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ReservationTypeVo {

    @ApiModelProperty
    private Long id;


    @ApiModelProperty(value = "预约类型")
    private String ReservationType;

    @ApiModelProperty(value = "预约类型 1-预约 2-到店")
    private int yesnoShopnote;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
