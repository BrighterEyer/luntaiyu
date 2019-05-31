package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.StoreVo;
import com.zhangzhao.common.dto.MasterTimeDto;
import com.zhangzhao.common.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

/**
 * 门店
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface StoreMapper {

	StoreVo beanToVo(Store store);

	@Mappings({@Mapping(target = "name", source = "reaName")})
	Store mdtoToBean(MasterTimeDto masterTimeDto);
}
