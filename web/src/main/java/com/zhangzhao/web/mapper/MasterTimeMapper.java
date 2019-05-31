package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.MasterTime;
import com.zhangzhao.web.vo.MasterTimeVo;

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
