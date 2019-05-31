package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.SlideshowImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 轮播图
 */
@Repository
public interface SlideshowImgRepository extends JpaRepository<SlideshowImg, Long>, JpaSpecificationExecutor<SlideshowImg> {

    Page<SlideshowImg> findByType(int type, Pageable pageable);

    Optional<SlideshowImg> findByType(int type);
}
