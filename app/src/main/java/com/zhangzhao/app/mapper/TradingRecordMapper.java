package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.TradingRecordVo;
import com.zhangzhao.common.entity.TradingRecord;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 交易记录
 */
@Component
@Mapper(componentModel = "spring")
public interface TradingRecordMapper {

    TradingRecordVo beanToVo(TradingRecord tradingRecord);

    TradingRecordVo beanToRestVo(TradingRecord tradingRecord);
}
