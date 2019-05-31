package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 预约类型
 */
@Repository
public interface ReservationtypeRepository extends JpaRepository<ReservationType, Long>, JpaSpecificationExecutor<ReservationType> {
}
