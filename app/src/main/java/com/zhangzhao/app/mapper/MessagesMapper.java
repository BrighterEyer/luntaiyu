package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.ActivityVo;
import com.zhangzhao.app.vo.MessagesVo;
import com.zhangzhao.common.entity.Activity;
import com.zhangzhao.common.entity.Messages;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 消息
 */
@Component
@Mapper(componentModel = "spring")
public interface MessagesMapper {

    MessagesVo beanToVo(Messages messages);
}
