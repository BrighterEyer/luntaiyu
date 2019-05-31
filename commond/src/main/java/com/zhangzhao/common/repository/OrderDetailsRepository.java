package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.OrderDetails;
import com.zhangzhao.common.entity.OrderSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单详情
 */
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>, JpaSpecificationExecutor<OrderDetails> {
    OrderDetails findAllByOrderId(Long id);

    List<OrderDetails> findByOrderId(Long orderId);

}
