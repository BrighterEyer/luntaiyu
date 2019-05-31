package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.MasterTimeVo;
import com.zhangzhao.common.entity.MasterTime;

/**
 * 师傅上下班时间
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface MasterTimeMapper {
	MasterTimeVo beanToVo(MasterTime masterTime);

}
