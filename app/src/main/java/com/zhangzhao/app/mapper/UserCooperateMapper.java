package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.UserCooperateVo;
import com.zhangzhao.common.entity.UserCooperate;

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
