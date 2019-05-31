package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.MemberVo;
import com.zhangzhao.common.entity.Member;

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
