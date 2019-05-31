package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.GoodsClassificationVo;
import com.zhangzhao.common.entity.GoodsClassification;

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

}
