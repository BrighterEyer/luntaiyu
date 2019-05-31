package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.PunchCardVo;
import com.zhangzhao.common.entity.PunchCard;

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
