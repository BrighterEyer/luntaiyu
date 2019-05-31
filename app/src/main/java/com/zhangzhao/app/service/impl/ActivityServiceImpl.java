package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ActivityService;
import com.zhangzhao.app.vo.ActivityVo;
import com.zhangzhao.common.entity.Activity;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 活动
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl extends BaseService implements ActivityService {

    @Override
    public StatusVo<ActivityVo> findAlls() {
        List<Activity> activityPage = activityRepository.findByEndTimeAfter(new Date());
        List<ActivityVo> collect = activityPage.parallelStream().map(activityMapper::beanToVo).collect(Collectors.toList());
        StatusVo<ActivityVo> vo = new StatusVo<>();
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusOneVo<ActivityVo> details(Long id) {
        StatusOneVo<ActivityVo> vo = new StatusOneVo<>();
        Optional<Activity> optional = activityRepository.findById(id);
        if (optional.isPresent()) {
            vo.success(activityMapper.beanToVo(optional.get()));
        }
        return vo;
    }

    @Override
    public List<Activity> findAllByGoodId(List<Long> ids) {
        List<Activity> list = activityRepository.findByGoodIdInAndStartTimeLessThanEqualAndEndTimeAfter(ids, new Date(), new Date());
        list = list.stream().filter(o -> o.getType() == 2).collect(Collectors.toList());
        return list;
    }
}
