package com.zhangzhao.web.mapper;

import com.zhangzhao.common.entity.admin.Admin;
import com.zhangzhao.web.vo.AdminVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 后台管理
 */
@Component
@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminVo beanToVo(Admin admin);
}
