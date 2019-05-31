package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 提现管理
 * 
 * @author Administrator
 *
 */
@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long>, JpaSpecificationExecutor<Withdraw> {

}
