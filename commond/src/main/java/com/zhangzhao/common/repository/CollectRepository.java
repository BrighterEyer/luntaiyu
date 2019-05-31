package com.zhangzhao.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.Collect;

/**
 * 收藏管理
 * 
 * @author Administrator
 *
 */
@Repository
public interface CollectRepository extends JpaRepository<Collect, Long>, JpaSpecificationExecutor<Collect> {

    Page<Collect> findByUserId(Long userId,Pageable pageable);

    int countByUserIdAndGoodsCommodity_Id(Long userId,Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from collect where user_id=?1 and product_id=?2", nativeQuery = true)
    void deleteByUserIdAndGoodsCommodity_Id(Long userId,Long id);
}
