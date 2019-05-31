package com.zhangzhao.web.security;

import com.zhangzhao.common.entity.admin.Admin;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AdminMpper {

    AdminUser adminToAdminUser(Admin admin);
}
