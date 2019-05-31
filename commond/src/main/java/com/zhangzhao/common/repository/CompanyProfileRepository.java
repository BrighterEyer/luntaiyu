package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 热门词汇
 */
@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long>, JpaSpecificationExecutor<CompanyProfile> {
    List<CompanyProfile> findByCityLike(String city);
}