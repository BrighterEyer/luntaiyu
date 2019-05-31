package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.QualityVo;
import com.zhangzhao.common.dto.QualityDto;
import com.zhangzhao.common.entity.Quality;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 质量处理
 */
@Component
@Mapper(componentModel = "spring")
public interface QualityMapper {

    QualityVo beanToVo(Quality quality);

    Quality dtoToBean(QualityDto qualityDto);
}
