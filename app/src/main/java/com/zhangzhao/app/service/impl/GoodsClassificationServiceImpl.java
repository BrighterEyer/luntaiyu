package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.vo.GoodsClassificationVo;
import com.zhangzhao.common.entity.GoodsClassification;
import com.zhangzhao.common.vo.StatusVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.GoodsClassificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 商品分类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsClassificationServiceImpl extends BaseService implements GoodsClassificationService {

    @Override
    public StatusVo<GoodsClassificationVo> findAll(Long id) {
        StatusVo<GoodsClassificationVo> vo=new StatusVo<>();
//        Pageable pageable = new PageRequest(0, 8, Sort.Direction.DESC, "id");
//        Page<GoodsClassification> page = goodsClassificationRepository.findByFuClassId(0, pageable);
//        List<GoodsClassificationVo> collect = page.getContent().parallelStream().map(goodsClassificationMapper::beanToVo).collect(Collectors.toList());
        List<GoodsClassification> list = goodsClassificationRepository.findAll();
        List<GoodsClassificationVo> two;
        List<GoodsClassificationVo> three;
        if (id==null){
            list=list.parallelStream().filter(o->o.getFuClassId()==0).collect(Collectors.toList());
            List<GoodsClassificationVo> collect = list.parallelStream().map(goodsClassificationMapper::beanToVo).collect(Collectors.toList());
            vo.success(collect);
        }else {
            List<GoodsClassification> collect;
            if (id==0){
                collect = list.parallelStream().filter(o -> o.getFuClassId() == (id==0?0:id)).collect(Collectors.toList());
            }else {
                collect = list.parallelStream().filter(o -> o.getId().equals(id)).collect(Collectors.toList());
            }
            List<GoodsClassificationVo> one = collect.parallelStream().map(goodsClassificationMapper::beanToVo).collect(Collectors.toList());
            for (GoodsClassificationVo goodsClassification : one){
                two = new ArrayList<>();
                for (GoodsClassification good : list){
                    if (goodsClassification.getId().equals(good.getFuClassId().toString())){
                        GoodsClassificationVo goodsClassificationVo = goodsClassificationMapper.beanToVo(good);
                        three = new ArrayList<>();
                        for (GoodsClassification o : list){
                            if (good.getId().equals(o.getFuClassId().toString())){
                                three.add(goodsClassificationMapper.beanToVo(o));
                            }
                        }
                        goodsClassificationVo.setTwo(three);
                        two.add(goodsClassificationVo);
                    }
                }
                goodsClassification.setTwo(two);
            }
            vo.success(one);
        }
        return vo;
    }
}
