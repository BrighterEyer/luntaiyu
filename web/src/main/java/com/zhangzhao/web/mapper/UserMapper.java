package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.User;
import com.zhangzhao.web.vo.UserVo;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
	UserVo beanToVo(User user);

}
