package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.WalletVo;
import com.zhangzhao.common.entity.Wallet;

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
