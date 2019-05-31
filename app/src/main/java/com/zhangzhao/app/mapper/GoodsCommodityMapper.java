package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.GoodsCommodityDetailsVo;
import com.zhangzhao.app.vo.GoodsPropersieVo;
import com.zhangzhao.app.vo.ProperstiesVo;
import com.zhangzhao.common.entity.Properties;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.GoodsCommodityVo;
import com.zhangzhao.common.entity.GoodsCommodity;

import java.util.List;

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

	GoodsCommodityDetailsVo beanToDetailsVo(GoodsCommodity goodsCommodity);

	List<ProperstiesVo> beanToGoodsPropersieVo(List<Properties> properties);

	GoodsPropersieVo beanToGoodsPropersieVo(GoodsCommodity goodsCommodity);
}
