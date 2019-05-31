package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.ServicesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 服务发放
 */
@Repository
public interface ServicesUserRepository extends JpaRepository<ServicesUser, Long>, JpaSpecificationExecutor<ServicesUser> {

}
