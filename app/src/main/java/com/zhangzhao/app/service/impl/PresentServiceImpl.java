package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.PresentService;
import com.zhangzhao.app.vo.*;
import com.zhangzhao.common.entity.Address;
import com.zhangzhao.common.entity.ForRecord;
import com.zhangzhao.common.entity.Present;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 礼品
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PresentServiceImpl extends BaseService implements PresentService {

    @Override
    public StatusOneVo findAll(Integer page, Integer pageSize) {
        User user = getUser();
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.Direction.DESC, "createTime");
        Page<Present> all = presentRepository.findAll(pageable);
        List<PresentVo> collect = all.stream().map(presentMapper::beanToVo).collect(Collectors.toList());
        StatusOneVo vo=new StatusOneVo();
        PresentWodeVo pvo=new PresentWodeVo();
        pvo.setPresentVos(collect);
        pvo.setIntegral(String.valueOf(user.getIntegral()));
        vo.success(pvo);
        return vo;
    }

    @Override
    public StatusOneVo findById(long id) {
        StatusOneVo vo=new StatusOneVo();
        Optional<Present> optional = presentRepository.findById(id);
        if (optional.isPresent()){
            PresentDetailsVo presentDetailsVo = presentMapper.beanToDetailsVo(optional.get());
            Optional<Address> first = getUser().getAddresses().parallelStream().filter(o -> o.getUsed() == 1).findFirst();
            if (first.isPresent()){
                presentDetailsVo.setAddressVo(addressMapper.beanToVo(first.get()));
            }
            vo.success(presentDetailsVo);
        }
        return vo;
    }

    @Override
    public StatusVoidVo exchange(long id,long addressId) {
        StatusVoidVo vo=new StatusVoidVo();
        User user = getUser();
        Optional<Present> optional = presentRepository.findById(id);
        if (optional.isPresent()){
            Present present = optional.get();
            if (user.getIntegral()>=present.getIntegral()){
                ForRecord forRecord=new ForRecord();
                forRecord.setUserId(user.getId());
                forRecord.setStatus(ForRecord.Status.THROUGH.getStatus());
                BeanUtils.copyProperties(present,forRecord,"id","createTime");
                Optional<Address> first = getUser().getAddresses().parallelStream().filter(o -> o.getId()==addressId).findFirst();
                if (first.isPresent()){
                    forRecord.setUserName(first.get().getName());
                    forRecord.setPhone(first.get().getPhone());
                    forRecord.setDetailed(first.get().getProvince()+first.get().getCity()+first.get().getDistrict()+first.get().getDetailed());
                }
                forRecordRepository.save(forRecord);
                vo.success();
            }else {
                vo.setMsg("积分不足");
            }
        }
        return vo;
    }

    @Override
    public StatusVo exchangeList(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.Direction.DESC, "createTime");
        Page<ForRecord> all = forRecordRepository.findByUserId(getUser().getId(), pageable);
        List<ForRecordVo> collect = all.stream().map(forRecordMapper::beanToVo).collect(Collectors.toList());
        StatusVo vo=new StatusVo();
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusOneVo exchangeDetail(long id) {
        StatusOneVo vo=new StatusOneVo();
        Optional<ForRecord> optional = forRecordRepository.findById(id);
        if (optional.isPresent()){
            ForRecordVo forRecordVo = forRecordMapper.beanToVo(optional.get());
            vo.success(forRecordVo);
        }
        return vo;
    }
}
