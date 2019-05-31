package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.CollectVo;
import com.zhangzhao.app.vo.IntegralVo;
import com.zhangzhao.common.entity.Collect;
import com.zhangzhao.common.entity.Integral;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 积分
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface IntegralMapper {

	IntegralVo beanToVo(Integral integral);
}
