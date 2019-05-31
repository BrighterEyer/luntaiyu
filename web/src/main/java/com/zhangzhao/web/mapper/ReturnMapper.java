package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.ReturnPolicy;
import com.zhangzhao.web.vo.ReturnVo;

/**
 * 退换货
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface ReturnMapper {
	ReturnVo beanToVo(ReturnPolicy return1);

}
