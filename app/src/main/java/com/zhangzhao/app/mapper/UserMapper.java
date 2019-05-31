package com.zhangzhao.app.mapper;

import com.zhangzhao.common.dto.MasterTimeDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.UserVo;
import com.zhangzhao.app.vo.UserWalletVo;
import com.zhangzhao.common.entity.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserVo beanToVo(User user);

	User dtoTobean(MasterTimeDto masterTimeDto);

    UserWalletVo userUserWalletVo(User user);

}
