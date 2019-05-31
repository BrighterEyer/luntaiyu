package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.Collect;
import com.zhangzhao.web.vo.CollectVo;

/**
 * 收藏
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface CollectMapper {
	
	CollectVo beanToVo(Collect collect);
}
