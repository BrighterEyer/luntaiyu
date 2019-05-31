package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.GoodSecurity;
import com.zhangzhao.web.vo.GoodSecurityVo;
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
