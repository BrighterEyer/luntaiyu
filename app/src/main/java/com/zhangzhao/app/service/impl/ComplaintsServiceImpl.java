package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ActivityService;
import com.zhangzhao.app.service.ComplaintsService;
import com.zhangzhao.common.entity.Complaints;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 投诉
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ComplaintsServiceImpl extends BaseService implements ComplaintsService {

    @Override
    public StatusVoidVo addComplaints(String content) {
        StatusVoidVo vo = new StatusVoidVo();
        Complaints complaints = new Complaints();
        complaints.setUser(getUser());
        complaints.setCause(content);
        complaintsRepository.save(complaints);
        vo.success();
        return vo;
    }
}
