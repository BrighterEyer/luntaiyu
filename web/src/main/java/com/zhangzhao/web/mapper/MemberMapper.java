package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.Member;
import com.zhangzhao.web.vo.MemberVo;

/**
 * 会员
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface MemberMapper {
	MemberVo beanToVo(Member member);

}
