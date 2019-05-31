package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.BankCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 银行卡
 */
@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long>, JpaSpecificationExecutor<BankCard> {

    Optional<BankCard> findByCardNumber(String cardNumber);

    Optional<BankCard> findByUserId(Long userId);

    Page<BankCard> findByUserIdAndStatus(Long userId, int status, Pageable pageable);
}
