package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.Comments;
import com.zhangzhao.web.vo.CommentsVo;

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
