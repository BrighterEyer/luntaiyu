package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.CollectVo;
import com.zhangzhao.common.entity.Collect;

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
