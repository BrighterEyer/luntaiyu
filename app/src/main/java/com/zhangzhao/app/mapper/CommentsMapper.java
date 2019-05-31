package com.zhangzhao.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.CommentsVo;
import com.zhangzhao.common.entity.Comments;

/**
 * 评论
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface CommentsMapper {
	CommentsVo beanToVo(Comments comments);
}
