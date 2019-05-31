package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.vo.OrderDetailsVo;
import com.zhangzhao.common.entity.Comments;
import com.zhangzhao.common.entity.OrderSupply;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.CommentsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 评论
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentsServiceImpl extends BaseService implements CommentsService {

    @Override
    public StatusVoidVo saveList(List<Comments> comments) {
        StatusVoidVo vo=new StatusVoidVo();
        commentsRepository.saveAll(comments);
        vo.success();
        return vo;
    }

    @Override
    public StatusVo product(Long id) {
        StatusVo vo=new StatusVo();
        Optional<OrderSupply> optional = orderSupplyRepository.findById(id);
        if (optional.isPresent()){
            OrderSupply orderSupply = optional.get();
            List<OrderDetailsVo> all = orderSupply.getOrderDetails().parallelStream().filter(o->o.getEvaluate()==0).map(orderDetailsMapper::beanToVo)
                    .collect(Collectors.toList());
            vo.success(all);
        }
        return vo;
    }
}
