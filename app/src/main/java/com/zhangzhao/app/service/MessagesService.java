package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;

public interface MessagesService extends CommonService {
    /**
     * 用户信息条数
     *
     * @return
     */
    StatusOneVo articleNumber(Long id);
}
