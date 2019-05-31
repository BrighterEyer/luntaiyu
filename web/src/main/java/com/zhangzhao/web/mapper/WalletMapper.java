package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.Wallet;
import com.zhangzhao.web.vo.WalletVo;

/**
 * 我的钱包
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface WalletMapper {
	WalletVo beanToVo(Wallet wallet);

}
