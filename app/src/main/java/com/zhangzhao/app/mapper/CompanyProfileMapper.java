package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.CompanyProfileVo;
import com.zhangzhao.common.entity.CompanyProfile;

/**
 * 热门
 */
@Component
@Mapper(componentModel = "spring")
public interface CompanyProfileMapper {

    CompanyProfileVo beanToVo(CompanyProfile companyProfile);
}
