package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.log.AdminLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 后台管理账号
 */
@Repository
public interface AdminLogRepository extends JpaRepository<AdminLog, Long>, JpaSpecificationExecutor<AdminLog> {
}
