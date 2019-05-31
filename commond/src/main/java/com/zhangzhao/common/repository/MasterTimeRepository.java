package com.zhangzhao.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.MasterTime;

/**
 * 师傅上下班管理
 * 
 * @author Administrator
 *
 */
@Repository
public interface MasterTimeRepository extends JpaRepository<MasterTime, Long>, JpaSpecificationExecutor<MasterTime> {


}
