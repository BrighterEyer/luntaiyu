package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.web.vo.AttentionVo;
import com.zhangzhao.common.entity.Attention;

/**
 * 关注
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface AttentionMapper {
	
	AttentionVo beanToVo(Attention attention);
}
