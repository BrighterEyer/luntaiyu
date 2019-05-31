package com.zhangzhao.web.mapper;

import com.zhangzhao.web.vo.ActivityVo;
import com.zhangzhao.common.entity.Activity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 活动
 */
@Component
@Mapper(componentModel = "spring")
public interface ActivityMapper {

    ActivityVo beanToVo(Activity activity);
}
