package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 购物车
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>, JpaSpecificationExecutor<ShoppingCart> {

    List<ShoppingCart> findByUserId(Long userId);

    void deleteByIdIn(List<Long> ids);

    @Modifying(clearAutomatically = true)
    @Query(value = "update shopping_cart set amount = :amount where id=:id",nativeQuery = true)
    void upAmount(@Param("id")Long id, @Param("amount")Integer amount);

    Optional<ShoppingCart> findByGoodIdAndInstallationTypeAndTypeAndUserId(Long id, String installationType, Integer type,Long userId);

    @Query(value = "select  installationtype from shopping_cart  where user_id=:id",nativeQuery = true)
    String selectCategory(Long id);
}
