package com.zhangzhao.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.PunchCard;

/**
 * 打卡管理
 * 
 * @author Administrator
 *
 */
@Repository
public interface PunchCardRepository extends JpaRepository<PunchCard, Long>, JpaSpecificationExecutor<PunchCard> {

}
