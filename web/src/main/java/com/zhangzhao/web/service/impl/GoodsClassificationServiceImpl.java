package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.GoodsClassificationDto;
import com.zhangzhao.common.entity.GoodsClassification;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.GoodsClassificationService;
import com.zhangzhao.web.vo.GoodsClassificationVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public StatusPageVo<GoodsClassificationVo> findAll(Integer page, Integer pageSize,Integer type,Long id) {
        StatusPageVo<GoodsClassificationVo> vo = new StatusPageVo<>();
        PageRequest pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "createTime");
        Page<GoodsClassification> list = goodsClassificationRepository.findAll(new Specification<GoodsClassification>() {
            @Override
            public Predicate toPredicate(Root<GoodsClassification> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (type!=null){
                    list.add(criteriaBuilder.equal(root.get("fuClassId"), id));
                }
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        },pageable);
//        List<GoodsClassificationVo> lists = Lists.newArrayList();
//        for (GoodsClassification goodsClassification: list.getContent()){
//            if (goodsClassification.getFuClassId()==0){
//                GoodsClassificationVo goodsClassificationVo = goodsClassificationMapper.beanToVo(goodsClassification);
//                List<GoodsClassificationVo> collect = list.getContent().parallelStream().filter(o -> goodsClassification.getId() == o.getFuClassId().longValue()).
//                        map(b->{
//                            GoodsClassificationVo goodsClassificationVo1 = goodsClassificationMapper.beanToVo(b);
//                            List<GoodsClassificationVo> collect1 = list.getContent().parallelStream().filter(o -> o.getFuClassId() == b.getId().longValue()).map
//                                    (goodsClassificationMapper::beanToVo).collect(Collectors.toList());
//                            goodsClassificationVo1.setChildren(collect1);
//                            return goodsClassificationVo1;
//                        }).collect(Collectors.toList());
//                goodsClassificationVo.setChildren(collect);
//                lists.add(goodsClassificationVo);
//            }
//        }
//        vo.success(lists, list.getTotalElements());
        List<GoodsClassificationVo> collect = list.stream().map(goodsClassificationMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect, list.getTotalElements());
        return vo;
    }

    @Override
    public StatusVo findAlls() {
        StatusVo vo=new StatusVo();
        List<GoodsClassification> all = goodsClassificationRepository.findAll();
        List<GoodsClassificationVo> collect = all.stream().map(goodsClassificationMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }

    @Override
    public Optional<GoodsClassification> findById(Long id) {
        return goodsClassificationRepository.findById(id);
    }

    @Override
    public StatusVoidVo saveB(GoodsClassificationDto goodsClassificationDto, BindingResult results) {
        StatusVoidVo vo = new StatusVoidVo();
        if (results.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, results.getFieldError().getDefaultMessage());
            return vo;
        }
        GoodsClassification goodsClassification = goodsClassificationMapper.dtoToBean(goodsClassificationDto);
        goodsClassificationRepository.save(goodsClassification);
        vo.success();
        return vo;
    }

    @Override
    public StatusVoidVo del(Long id) {
        StatusVoidVo vo = new StatusVoidVo();
        if (id>0){
            goodsClassificationRepository.deleteByIdOrFuClassId(id,id);
            vo.success();
        }
        return vo;
    }
}
