package com.zhangzhao.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class WebOrderDto {

    @NotNull(message = "订单id不能为空")
    @ApiModelProperty
    private Long id;

    @NotNull(message = "收货人不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotNull(message = "省不能为空")
    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "省ID")
    private Long provinceId;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "市ID")
    private Long cityId;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "区ID")
    private Long districtId;

    @ApiModelProperty(value = "详细地址")
    private String detailed;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "预安装时间")
    private Date preInstallationTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "上门安装时间")
    private Date storeInstallationTime;

    @ApiModelProperty(value = "补发物流")
    private String reissue;

    @NotNull(message = "订单总价格不能为空")
    @ApiModelProperty(value = "订单支付价格",required = true)
    private double paymentPrcie;

    @ApiModelProperty(value = "备注")
    private String remark;

}
