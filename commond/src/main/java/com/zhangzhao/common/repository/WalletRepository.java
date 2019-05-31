package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 我的钱包管理
 *
 * @author Administrator
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>, JpaSpecificationExecutor<Wallet> {


    @Modifying(clearAutomatically = true)
    @Query(value = "update wallet w set w.balance = w.balance-:balance  where w.user_id=:id", nativeQuery = true)
    void updateBalance(@Param("id") Long id, @Param("balance") double balance);
}
