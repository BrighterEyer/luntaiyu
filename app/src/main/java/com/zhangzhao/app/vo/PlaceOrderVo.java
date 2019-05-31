package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class PlaceOrderVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "安装费")
    private String installationFee="0";

    @ApiModelProperty(value = "运费")
    private String freight="0";

    @ApiModelProperty(value = "商品金额")
    private String sum;

    @ApiModelProperty(value = "0-有运费 1-运费另计")
    private String type="0";

    @ApiModelProperty(value = "积分")
    private String integral="0";

    @ApiModelProperty(value = "默认地址")
    private AddressVo address;

    @ApiModelProperty(value = "购买的商品")
    private List<GoodsCommodityVo> list;

    @ApiModelProperty(value = "优惠价格")
    private String tariff="0";

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "上班时间")
    private Date gotoWork;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "下班时间")
    private Date getoffWork;
}
