package com.zhangzhao.web.mapper;

import com.zhangzhao.common.entity.CompanyProfile;
import com.zhangzhao.web.vo.CompanyProfileVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 公司简介
 */
@Component
@Mapper(componentModel = "spring")
public interface CompanyProfileMapper {

    CompanyProfileVo beanToVo(CompanyProfile companyProfile);
}
