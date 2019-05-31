package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.GoodSecurityVo;
import com.zhangzhao.common.entity.GoodSecurity;
/**
 * 商品保障
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface GoodSecurityMapper {
	
	GoodSecurityVo beanToVo(GoodSecurity goodSecurity);

}
