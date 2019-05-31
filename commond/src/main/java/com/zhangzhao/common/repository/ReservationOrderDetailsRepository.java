package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.ReservationOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationOrderDetailsRepository extends JpaRepository<ReservationOrderDetails, Long>, JpaSpecificationExecutor<ReservationOrderDetails> {

}
