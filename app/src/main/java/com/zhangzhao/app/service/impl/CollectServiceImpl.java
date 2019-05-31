package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.vo.CollectVo;
import com.zhangzhao.common.entity.Address;
import com.zhangzhao.common.entity.Collect;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.CollectService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 收藏
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CollectServiceImpl extends BaseService implements CollectService {

    @Override
    public StatusVo findAll(Integer page, Integer pageSize, String... p) {
        StatusVo vo=new StatusVo();
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.Direction.DESC, "createTime");
        Page<Collect> all = collectRepository.findByUserId(getUser().getId(), pageable);
        List<CollectVo> collect = all.getContent().parallelStream().map(collectMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusVoidVo save(Long id, Integer type) {
        StatusVoidVo vo=new StatusVoidVo();
        if (id>0 && type==1){
            Collect collect=new Collect();
            Optional<GoodsCommodity> optional = goodsCommodityRepository.findById(id);
            if (optional.isPresent()){
                collect.setGoodsCommodity(optional.get());
                collect.setUserId(getUser().getId());
                collectRepository.save(collect);
            }
        }else if(id>0 && type==2){
            collectRepository.deleteByUserIdAndGoodsCommodity_Id(getUser().getId(),id);
        }
        vo.success();
        return vo;
    }
}
