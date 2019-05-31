package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 投诉
 */
@Repository
public interface ComplaintsRepository extends JpaRepository<Complaints, Long>, JpaSpecificationExecutor<Complaints> {
}
