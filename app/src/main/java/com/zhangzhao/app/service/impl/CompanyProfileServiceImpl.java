package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.CompanyProfileService;
import com.zhangzhao.app.vo.CompanyProfileVo;
import com.zhangzhao.common.entity.CompanyProfile;
import com.zhangzhao.common.vo.StatusVo;
import jodd.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 热门搜索词
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyProfileServiceImpl extends BaseService implements CompanyProfileService {

    @Override
    public StatusVo findAlls() {
        Pageable pageable = PageRequest.of(0, 8, Sort.Direction.DESC, "cout");
        Page<CompanyProfile> all = companyProfileRepository.findAll(pageable);
        List<CompanyProfileVo> collect = all.getContent().parallelStream().map(companyProfileMapper::beanToVo).collect(Collectors.toList());
        StatusVo vo=new StatusVo();
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusVo citys() {
        Pageable pageable = PageRequest.of(0, 8, Sort.Direction.DESC, "region");
        Page<CompanyProfile> all = companyProfileRepository.findAll(pageable);
        List<CompanyProfileVo> collect = all.getContent().parallelStream().map(companyProfileMapper::beanToVo).collect(Collectors.toList());
        StatusVo vo=new StatusVo();
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusVo crtyList() {
        StatusVo vo=new StatusVo();
        List<CompanyProfile> company = companyProfileRepository.findAll();
        vo.success(company);
        return vo;
    }

    @Override
    public StatusVo findByLikeList(String city) {
        StatusVo vo = new StatusVo();
        List<CompanyProfile> companyProfile = companyProfileRepository.findByCityLike("%"+city + "%");
        vo.success(companyProfile);
        return vo;
    }

}
