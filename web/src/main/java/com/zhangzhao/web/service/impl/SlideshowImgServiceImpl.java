package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.SlideshowImgDto;
import com.zhangzhao.common.entity.SlideshowImg;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.SlideshowImgService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Optional;

/**
 * 轮播图
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SlideshowImgServiceImpl extends BaseService implements SlideshowImgService {

    @Override
    public StatusPageVo<SlideshowImg> findAll(Integer page, Integer pageSize) {
        PageRequest pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "type");
        Page<SlideshowImg> list = slideshowImgRepository.findAll(pageable);
        StatusPageVo<SlideshowImg> vo = new StatusPageVo<>();
        vo.success(list.getContent(), list.getTotalElements());
        return vo;
    }

    @Override
    public StatusVoidVo imgDel(Long id) {
        StatusVoidVo vo = new StatusVoidVo();
        slideshowImgRepository.deleteById(id);
        vo.success();
        return vo;
    }

    @Override
    public StatusVoidVo saveImg(SlideshowImgDto slideshowImgDto, BindingResult results) {
        StatusVoidVo vo = new StatusVoidVo();
        if (results.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, results.getFieldError().getDefaultMessage());
            return vo;
        }
        if(slideshowImgDto.getType()==2){
            Optional<SlideshowImg> optional = slideshowImgRepository.findByType(SlideshowImg.Type.ACTIVITY.getType());
            if (optional.isPresent() && (slideshowImgDto.getId()==null || optional.get().getId()!=slideshowImgDto.getId().longValue())){
                vo.setMsg("活动图片只能上传一张");
                return vo;
            }
        }
        SlideshowImg slideshowImg = slideshowImgMapper.dtoToBean(slideshowImgDto);
        slideshowImgRepository.saveAndFlush(slideshowImg);
        vo.success();
        return vo;
    }

    @Override
    public Optional<SlideshowImg> findById(Long id) {
        return slideshowImgRepository.findById(id);
    }
}
