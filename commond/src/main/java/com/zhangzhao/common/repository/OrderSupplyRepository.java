package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.OrderSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单
 */
@Repository
public interface OrderSupplyRepository extends JpaRepository<OrderSupply, Long>, JpaSpecificationExecutor<OrderSupply> {

    OrderSupply findByOrderNumber(String orderNumber);

    List<OrderSupply> findByUserId(Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "update order_supply set status = :status where id=:id", nativeQuery = true)
    void updateStatus(@Param("id") Long id, @Param("status") int status);

    @Modifying(clearAutomatically = true)
    @Query(value = "update order_supply set status = :status,complete_time= now() where id=:id", nativeQuery = true)
    void updateStatusAndCompleteTime(@Param("id") Long id, @Param("status") int status);

}
