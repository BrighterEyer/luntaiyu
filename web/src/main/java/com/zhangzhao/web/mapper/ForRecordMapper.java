package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.ForRecord;
import com.zhangzhao.web.vo.ForRecordVo;

/**
 * 兑换记录
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface ForRecordMapper {
	ForRecordVo beanToVo(ForRecord forRecord);
}
