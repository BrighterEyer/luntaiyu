package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.TradingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 交易记录管理
 *
 * @author Administrator
 */
@Repository
public interface TradingRecordRepository
        extends JpaRepository<TradingRecord, Long>, JpaSpecificationExecutor<TradingRecord> {


}
