package com.zhangzhao.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.ForRecord;

/**
 * 兑换记录管理
 * 
 * @author Administrator
 *
 */
@Repository
public interface ForRecordRepository extends JpaRepository<ForRecord, Long>, JpaSpecificationExecutor<ForRecord> {

    Page<ForRecord> findByUserId(Long userId,Pageable pageable);
}
