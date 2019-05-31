package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 门店
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, JpaSpecificationExecutor<Store> {

}
