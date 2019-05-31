package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.UserCooperate;
import com.zhangzhao.web.vo.UserCooperateVo;

/**
 * 用户搭档
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface UserCooperateMapper {
	UserCooperateVo beanToVo(UserCooperate userCooperate);

}
