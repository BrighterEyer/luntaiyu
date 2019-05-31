package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.PrecontractEvaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 预约评价管理
 *
 * @author Administrator
 */
@Repository
public interface PrecontractEvaluateRepository extends JpaRepository<PrecontractEvaluate, Long>, JpaSpecificationExecutor<PrecontractEvaluate> {


}
