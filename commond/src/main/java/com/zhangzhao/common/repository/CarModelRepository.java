package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 车型
 */
@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long>, JpaSpecificationExecutor<CarModel> {

}
