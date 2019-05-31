package com.zhangzhao.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.GoodSecurity;

/**
 * 商品保障管理
 * 
 * @author Administrator
 *
 */
@Repository
public interface GoodSecurityRepository
		extends JpaRepository<GoodSecurity, Long>, JpaSpecificationExecutor<GoodSecurity> {

}
