package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.TradingRecordService;
import com.zhangzhao.app.vo.TradingRecordVo;
import com.zhangzhao.common.entity.TradingRecord;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 交易记录
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TradingRecordServiceImpl extends BaseService implements TradingRecordService {

    @Override
    public StatusVoidVo saveBean(Object object) {
        StatusVoidVo vo = new StatusVoidVo();
        tradingRecordRepository.saveAndFlush((TradingRecord) object);
        return vo;
    }

    @Override
    public StatusOneVo<TradingRecordVo> findById(Long id) {
        StatusOneVo<TradingRecordVo> vo = new StatusOneVo<>();
        TradingRecord tradingRecord = tradingRecordRepository.getOne(id);
        TradingRecordVo tradingRecordVo = tradingRecordMapper.beanToRestVo(tradingRecord);
        vo.success(tradingRecordVo);
        return vo;
    }
}
