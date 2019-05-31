package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.Withdraw;
import com.zhangzhao.web.vo.WithdrawVo;

/**
 * 提现
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface WithdrawMapper {
	WithdrawVo beanToVo(Withdraw withdraw);

}
