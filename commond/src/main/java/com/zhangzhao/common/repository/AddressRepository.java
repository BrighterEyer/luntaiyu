package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 收货地址
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    Optional<Address> findByUsedAndUserId(int aLong, Long userId);

    Optional<Address> findByUserId(Long id);

    Page<Address> findByUserId(Long id, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query(value = "update address set used=0 where used=1 and user_id=:userId", nativeQuery = true)
    void closed(@Param("userId") Long userId);
}
