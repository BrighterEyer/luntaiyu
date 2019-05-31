package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.PunchCard;
import com.zhangzhao.web.vo.PunchCardVo;

/**
 *  打卡
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface PunchCardMapper {
	PunchCardVo beanToVo(PunchCard punchCard);
}
