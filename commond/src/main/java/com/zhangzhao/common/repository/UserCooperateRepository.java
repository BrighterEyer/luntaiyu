package com.zhangzhao.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.UserCooperate;

/**
 * 用户搭档管理
 * 
 * @author Administrator
 *
 */
@Repository
public interface UserCooperateRepository
		extends JpaRepository<UserCooperate, Long>, JpaSpecificationExecutor<UserCooperate> {

}
