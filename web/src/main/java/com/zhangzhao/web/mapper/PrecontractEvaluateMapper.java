package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.PrecontractEvaluate;
import com.zhangzhao.web.vo.PrecontractEvaluateVo;

/**
 * 预约评价
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface PrecontractEvaluateMapper {
	PrecontractEvaluateVo beanToVo(PrecontractEvaluate precontractEvaluate);

}
