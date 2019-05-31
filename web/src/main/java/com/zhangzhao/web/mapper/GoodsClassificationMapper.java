package com.zhangzhao.web.mapper;

import com.zhangzhao.common.dto.GoodsClassificationDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.GoodsClassification;
import com.zhangzhao.web.vo.GoodsClassificationVo;

/**
 * 商品分类
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface GoodsClassificationMapper {
	GoodsClassificationVo beanToVo(GoodsClassification goodsClassification);

	GoodsClassification dtoToBean(GoodsClassificationDto goodsClassificationDto);
}
