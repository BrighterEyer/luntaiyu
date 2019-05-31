package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.ActivityVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.entity.Activity;
import com.zhangzhao.common.vo.StatusOneVo;

import java.util.List;

public interface ActivityService extends CommonService {

    StatusOneVo<ActivityVo> details(Long id);

    List<Activity> findAllByGoodId(List<Long> ids);
}
