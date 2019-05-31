package com.zhangzhao.web.mapper;

import com.zhangzhao.common.dto.SlideshowImgDto;
import com.zhangzhao.common.entity.SlideshowImg;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 活动
 */
@Component
@Mapper(componentModel = "spring")
public interface SlideshowImgMapper {

    SlideshowImg beanToVo(SlideshowImg slideshowImg);

    SlideshowImg dtoToBean(SlideshowImgDto slideshowImgDto);
}
