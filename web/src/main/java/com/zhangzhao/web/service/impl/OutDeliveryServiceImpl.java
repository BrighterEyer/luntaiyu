package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.dto.OutDeliveryDto;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.OutDelivery;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.OutDeliveryService;
import com.zhangzhao.web.vo.GoodsCommodityVo;
import com.zhangzhao.web.vo.OutDeliveryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.stream.Collectors;

/**
 * 出库信息
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OutDeliveryServiceImpl extends BaseService implements OutDeliveryService {


    @Override
    public StatusPageVo findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "createTime");
        Page<OutDelivery> all = outDeliveryRepository.findAll(new Specification<OutDelivery>() {
            @Override
            public Predicate toPredicate(Root<OutDelivery> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        }, pageable);
        List<OutDeliveryVo> collect = all.stream().map(outDeliveryMapper::beanToVo).collect(Collectors.toList());
        StatusPageVo vo = new StatusPageVo();
        vo.success(collect, all.getTotalElements());
        return vo;
    }

    /**
     * 出库
     *
     * @param outDeliveryDto
     * @param result
     * @return
     */
    @Override
    public StatusOneVo<OutDelivery> outinventory(OutDeliveryDto outDeliveryDto, BindingResult result) {
        StatusOneVo<OutDelivery> vo = new StatusOneVo<>();
        if (result.hasErrors()) {
            vo.setMsg("参数错误");
        } else {

            int Inventory = goodsCommodityRepository.findInventory(outDeliveryDto.getGoodId());//得到该商品的库存数
            if (outDeliveryDto.getDeliveryCount() > Inventory && Inventory == 0) {
                vo.setMsg("库存不足,请添加库存");
                return vo;
            } else {
                int i = Inventory - outDeliveryDto.getDeliveryCount();//得到出库以后的库存数
                goodsCommodityRepository.couponUpdate(outDeliveryDto.getGoodId(), i);//更新库存
                OutDelivery outDelivery = new OutDelivery();
                outDelivery.setTyreNumber(outDeliveryDto.getTyreNumber());
                outDelivery.setDeliveryCount(outDeliveryDto.getDeliveryCount());
                int delivery = goodsCommodityRepository.finddelivery(outDeliveryDto.getGoodId());//得到出库总数
                int i2 = delivery + outDeliveryDto.getDeliveryCount();
                goodsCommodityRepository.deliveryUpdate(outDeliveryDto.getGoodId(), i2);//更新出库总数
                outDelivery.setGoodId(outDeliveryDto.getGoodId());
                outDelivery.setDeliveryCause(outDeliveryDto.getDeliveryCause());
                outDelivery.setDeliveryDetail(outDeliveryDto.getDeliveryDetail());
                OutDelivery deliver = outDeliveryRepository.saveAndFlush(outDelivery);
                vo.success(deliver);
            }
        }
        return vo;
    }


    @Override
    public StatusPageVo findAll(Integer page, Integer pageSize, String name) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "createTime");
        Page<GoodsCommodity> all = goodsCommodityRepository.findAll(new Specification<GoodsCommodity>() {
            @Override
            public Predicate toPredicate(Root<GoodsCommodity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(name)) {
                    Predicate names = criteriaBuilder.like(root.get("name"), "%" + name + "%");
                    list.add(criteriaBuilder.or(names));
                }
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        }, pageable);
        List<GoodsCommodityVo> collect = all.stream().map(goodsCommodityMapper::beanToVo).collect(Collectors.toList());
        StatusPageVo vo = new StatusPageVo();
        vo.success(collect, all.getTotalElements());
        return vo;
    }

    @Override
    public StatusOneVo<GoodsCommodity> couponUpdate(Long id, int inventory) {
        StatusOneVo<GoodsCommodity> vo = new StatusOneVo<>();
        GoodsCommodity goodsCommodity = new GoodsCommodity();
        goodsCommodity.setId(id);
        goodsCommodity.setInventory(inventory);
        goodsCommodityRepository.couponUpdate(id, inventory);
        vo.success();
        return vo;
    }
}
