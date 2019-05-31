package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.StoreService;
import com.zhangzhao.app.vo.StoreVo;
import com.zhangzhao.common.entity.Store;
import com.zhangzhao.common.vo.StatusVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 门店
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreServiceImpl extends BaseService implements StoreService {


    @Override
    public StatusVo<StoreVo> findAlls() {
        StatusVo<StoreVo> vo = new StatusVo<>();
        List<Store> storeList = storeRepository.findAll();
        List<StoreVo> collect = storeList.parallelStream().map(storeMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }
}
