package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 商品属性
 * 
 * @author Administrator
 *
 */
@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Long>, JpaSpecificationExecutor<Properties> {

    void deleteByIdOrParentId(long id,long parentId);
}
