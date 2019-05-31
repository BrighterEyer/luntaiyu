package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 流程
 */
@Repository
public interface FlowRepository extends JpaRepository<Flow, Long>, JpaSpecificationExecutor<Flow> {

    List<Flow> findByUserId(Long userId);
}
