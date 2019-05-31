package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.ForRecordVo;
import com.zhangzhao.common.entity.ForRecord;

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
