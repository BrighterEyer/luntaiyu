package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.ReturnPolicyVo;
import com.zhangzhao.common.entity.ReturnPolicy;

/**
 * 退换货
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface ReturnPolicyMapper {
	ReturnPolicyVo beanToVo(ReturnPolicy return1);

}
