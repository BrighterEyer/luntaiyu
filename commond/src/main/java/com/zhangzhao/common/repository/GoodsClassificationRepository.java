package com.zhangzhao.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.GoodsClassification;
/**
 * 商品分类
 * @author Administrator
 *
 */
@Repository
public interface GoodsClassificationRepository
		extends JpaRepository<GoodsClassification, Long>, JpaSpecificationExecutor<GoodsClassification> {

	Page<GoodsClassification> findByFuClassId(int fu, Pageable pageable);

	void deleteByIdOrFuClassId(long id,long fid);
}
