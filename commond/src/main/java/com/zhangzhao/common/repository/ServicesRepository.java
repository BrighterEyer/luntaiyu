package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 服务
 */
@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>, JpaSpecificationExecutor<Services> {

    List<Services> findByTypeIn(List<Integer> types);
}
