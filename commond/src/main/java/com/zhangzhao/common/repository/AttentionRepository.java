package com.zhangzhao.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.Attention;

/**
 * 关注管理
 * @author Administrator
 *
 */
@Repository
public interface AttentionRepository extends JpaRepository<Attention, Long>, JpaSpecificationExecutor<Attention> {
	
}
