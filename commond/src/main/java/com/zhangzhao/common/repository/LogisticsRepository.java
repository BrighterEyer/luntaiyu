package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 物流
 */
@Repository
public interface LogisticsRepository extends JpaRepository<Logistics, Long>, JpaSpecificationExecutor<Logistics> {

}
