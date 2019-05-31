package com.zhangzhao.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.ReturnPolicy;

/**
 * 退换货管理
 * 
 * @author Administrator
 *
 */
@Repository
public interface ReturnPolicyRepository extends JpaRepository<ReturnPolicy, Long>, JpaSpecificationExecutor<ReturnPolicy> {

    Page<ReturnPolicy> findByUserId(Long userId, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query(value = "update return_policy set status = :status where id=:id",nativeQuery = true)
    void upStatus(@Param("id")Long id, @Param("status")Integer status);

    @Modifying(clearAutomatically = true)
    @Query(value = "update return_policy set returns_number = :number,returns_name =:returnsName  where id=:id",nativeQuery = true)
    void reissueNumber(@Param("id")Long id, @Param("number")String number, @Param("returnsName")String returnsName);



}
