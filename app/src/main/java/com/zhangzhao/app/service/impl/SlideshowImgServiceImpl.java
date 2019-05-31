package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ActivityService;
import com.zhangzhao.app.service.SlideshowImgService;
import com.zhangzhao.app.vo.GoodsClassificationVo;
import com.zhangzhao.app.vo.GoodsCommodityVo;
import com.zhangzhao.app.vo.MainVo;
import com.zhangzhao.app.vo.SlideshowImgVo;
import com.zhangzhao.common.entity.Activity;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.SlideshowImg;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 轮播图
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SlideshowImgServiceImpl extends BaseService implements SlideshowImgService {

    @Override
    public StatusOneVo<MainVo> slideshowImgList(Integer page, Integer pageSize) {
        MainVo mainVo = new MainVo();
        Pageable pageable = PageRequest.of(0, 4, Sort.Direction.DESC, "id");
        Page<SlideshowImg> slideshowImgs = slideshowImgRepository.findByType(1, pageable);
        List<SlideshowImgVo> collect = slideshowImgs.getContent().parallelStream().map(slideshowImgMapper::beanToVo).collect(Collectors.toList());
        StatusVo<GoodsClassificationVo> statusVo = goodsClassificationService.findAll(null);
        Optional<SlideshowImg> optionalSlideshowImg = slideshowImgRepository.findByType(2);
        List<Activity> activityPage = activityRepository.findByEndTimeAfter(new Date());
        Pageable pageable1 = PageRequest.of(page-1, pageSize, Sort.Direction.DESC, "createTime");
        Page<GoodsCommodity> goodsCommodityPage = goodsCommodityRepository.findByNoyesHotsell("1", pageable1);
        List<GoodsCommodityVo> goodsCommodityVoList = goodsCommodityPage.getContent().parallelStream().map(goodsCommodityMapper::beanToVo).collect(Collectors.toList());

        mainVo.setCollect(collect);
        mainVo.setGoodsClassificationVos(statusVo.getData());
        mainVo.setSlideshowImgVo(optionalSlideshowImg.isPresent()?slideshowImgMapper.beanToVo(optionalSlideshowImg.get()):null);
        mainVo.setEndTime(String.valueOf(activityPage.size()));
        mainVo.setGoodsCommodityVoList(goodsCommodityVoList);
        StatusOneVo<MainVo> vo = new StatusOneVo<>();
        vo.success(mainVo);
        return vo;
    }
}
