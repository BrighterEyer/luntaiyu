package com.zhangzhao.web.mapper;

import com.zhangzhao.common.dto.GoodsCommodityDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.web.vo.GoodsCommodityVo;

/**
 * 商品信息
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface GoodsCommodityMapper {
	GoodsCommodityVo beanToVo(GoodsCommodity goodsCommodity);

	GoodsCommodity dtoToBean(GoodsCommodityDto goodsCommodityDto);
}
