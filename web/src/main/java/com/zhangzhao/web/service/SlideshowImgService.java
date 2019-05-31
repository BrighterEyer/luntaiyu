package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.SlideshowImgDto;
import com.zhangzhao.common.entity.SlideshowImg;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface SlideshowImgService extends CommonService {

    StatusPageVo<SlideshowImg> findAll(Integer page, Integer pageSize);

    StatusVoidVo saveImg(SlideshowImgDto slideshowImgDto, BindingResult results);

    StatusVoidVo imgDel(Long id);

    Optional<SlideshowImg> findById(Long id);
}
