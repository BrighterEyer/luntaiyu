package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.ActivityVo;
import com.zhangzhao.app.vo.SlideshowImgVo;
import com.zhangzhao.common.entity.Activity;
import com.zhangzhao.common.entity.SlideshowImg;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 轮播图
 */
@Component
@Mapper(componentModel = "spring")
public interface SlideshowImgMapper {

    SlideshowImgVo beanToVo(SlideshowImg slideshowImg);
}
