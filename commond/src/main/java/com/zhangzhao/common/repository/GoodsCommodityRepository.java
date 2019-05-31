package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Properties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zhangzhao.common.entity.GoodsCommodity;

import java.util.List;
import java.util.Optional;

/**
 * 商品信息管理
 *
 * @author Administrator
 */
@Repository
public interface GoodsCommodityRepository extends JpaRepository<GoodsCommodity, Long>, JpaSpecificationExecutor<GoodsCommodity> {

    Page<GoodsCommodity> findByNoyesHotsell(String noyesHotsell, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query(value = "update goods_commodity set inventory = inventory-:inventory,sales = sales+:sales where id=:id", nativeQuery = true)
    void upIS(@Param("id") Long id, @Param("inventory") int inventory, @Param("sales") int sales);

    List<GoodsCommodity> findBySeries(String series);

    List<GoodsCommodity> findByIdIn(String ids);

    @Query(value = "select inventory FROM goods_commodity WHERE id=:id", nativeQuery = true)
    int findInventory(@Param("id") Long id);

    @Query(value = "select delivery FROM goods_commodity WHERE id=:id", nativeQuery = true)
    int finddelivery(@Param("id") Long id);

    Optional<GoodsCommodity> findBySeriesAndProperties(String series, List<Properties> properties);

    @Modifying(clearAutomatically = true)
    @Query(value = "update goods_commodity set inventory =:inventory where id=:id", nativeQuery = true)
    void couponUpdate(@Param("id") Long id, @Param("inventory") int inventory);

    @Modifying(clearAutomatically = true)
    @Query(value = "update goods_commodity set delivery =:delivery where id=:id", nativeQuery = true)
    void deliveryUpdate(@Param("id") Long id, @Param("delivery") int delivery);

    @Query(value = "select delivery FROM goods_commodity WHERE id=:id", nativeQuery = true)
    GoodsCommodity finaById(@Param("id") Long id);
}
