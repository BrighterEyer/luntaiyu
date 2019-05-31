package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Integral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 积分记录
 */
@Repository
public interface IntegralRepository extends JpaRepository<Integral, Long>, JpaSpecificationExecutor<Integral> {

    Page<Integral> findByUserId(Long userId,Pageable pageable);
}
