package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.TradingRecordVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;

public interface TradingRecordService extends CommonService {


    /**
     * 交易记录
     *
     * @return
     */
    StatusOneVo<TradingRecordVo> findById(Long userId);
}
