package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.OrderDetailsDto;
import com.zhangzhao.common.dto.OrderSupplyDto;
import com.zhangzhao.common.dto.WebOrderDto;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.OrderDetails;
import com.zhangzhao.common.entity.OrderSupply;
import com.zhangzhao.common.util.UtilDate;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.OrderSupplyService;
import com.zhangzhao.web.vo.OrderSupplyVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 订单
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderSupplyServiceImpl extends BaseService implements OrderSupplyService {

    @Override
    public StatusPageVo findAll(Integer page, Integer pageSize, String keyword, Integer status) {
        StatusPageVo vo=new StatusPageVo();
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.Direction.DESC, "createTime");
        Page<OrderSupply> all = orderSupplyRepository.findAll(new Specification<OrderSupply>() {
            @Override
            public Predicate toPredicate(Root<OrderSupply> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(keyword)) {
                    Predicate id = criteriaBuilder.like(root.get("id"), "%" + Long.valueOf(keyword) + "%");
                    Join<OrderDetails, OrderSupply> joinType = root.join("orderDetails", JoinType.LEFT);
                    Predicate name = criteriaBuilder.like(joinType.get("name"), "%" + keyword + "%");
                    Predicate orderNumber = criteriaBuilder.like(joinType.get("orderNumber"), "%" + keyword + "%");
                    list.add(criteriaBuilder.or(name, orderNumber));
                }
                if (status != null) {
                    list.add(criteriaBuilder.equal(root.get("status"), status));
                }
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        }, pageable);
        List<OrderSupplyVo> collect = all.stream().map(orderSupplyMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect,all.getTotalElements());
        return vo;
    }

    @Override
    public OrderSupply findById(Long id) {
        return orderSupplyRepository.findById(id).orElseThrow(()->new RuntimeException("id是"+id+"的订单为空"));
    }

    @Override
    public StatusVoidVo saveOrder(WebOrderDto webOrderDto, BindingResult bindingResult) {
        StatusVoidVo vo = new StatusVoidVo();
        if (bindingResult.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, bindingResult.getFieldError().getDefaultMessage());
        } else {
            Optional<OrderSupply> optional = orderSupplyRepository.findById(webOrderDto.getId());
            if (optional.isPresent()){
                OrderSupply orderSupply1 = optional.get();
                BeanUtils.copyProperties(webOrderDto,orderSupply1);
                orderSupplyRepository.save(orderSupply1);
                vo.success();
            }else {
                vo.setMsg("订单不存在");
            }
        }
        return vo;
    }

    @Override
    public StatusVoidVo updateStatus(Long id, int status) {
        StatusVoidVo vo=new StatusVoidVo();
        if (status==4){
            orderSupplyRepository.updateStatusAndCompleteTime(id,status);
        }else {
            orderSupplyRepository.updateStatus(id,status);
        }
        vo.success();
        return vo;
    }
}
