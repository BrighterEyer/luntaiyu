package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.entity.Comments;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;

import java.util.List;

public interface CommentsService extends CommonService {

    StatusVoidVo saveList(List<Comments> comments);

    StatusVo product(Long id);
}
