package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Quality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 质量处理
 */
@Repository
public interface QualityRepository extends JpaRepository<Quality, Long>, JpaSpecificationExecutor<Quality> {
    
    Page<Quality> findByUserId(Long userId, Pageable pageable);
}
