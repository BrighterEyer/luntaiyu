package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.OutDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 出库信息
 */
@Repository
public interface OutDeliveryRepository extends JpaRepository<OutDelivery, Long>, JpaSpecificationExecutor<OutDelivery> {

}
