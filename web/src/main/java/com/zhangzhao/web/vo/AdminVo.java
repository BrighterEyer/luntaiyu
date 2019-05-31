package com.zhangzhao.web.vo;

import com.zhangzhao.common.entity.admin.Authority;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class AdminVo {

    @ApiModelProperty
    private String id;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "父类")
    private String parentId;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "权限")
    private List<Authority> authority;
}
