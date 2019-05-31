package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Present;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 礼品
 */
@Repository
public interface PresentRepository extends JpaRepository<Present, Long>, JpaSpecificationExecutor<Present> {

}
