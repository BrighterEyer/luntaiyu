package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusVoidVo;

public interface ComplaintsService extends CommonService {

    StatusVoidVo addComplaints(String content);
}
