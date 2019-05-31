package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.WithdrawVo;
import com.zhangzhao.common.entity.Withdraw;

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
