package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 优惠券
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>, JpaSpecificationExecutor<Coupon> {

    @Modifying
    @Query(value = "update coupon set status = :status where id=:id", nativeQuery = true)
    void upStatus(@Param("id") Long id, @Param("status") int status);

    @Modifying
    @Query(value = "delete from coupon where id=?1", nativeQuery = true)
    void delCoupon(Long id);

    @Query(value = " select sum(price) from coupon", nativeQuery = true)
    double sumByPrice(double price);

    @Query(value = " select count(*) from coupon where type=1", nativeQuery = true)
    int countByPrice();

    @Query(value = " select count(*) from coupon where type=2", nativeQuery = true)
    int couponByDjin();

    @Query(value = " select sum(price) from coupon where status=0", nativeQuery = true)
    double countyiShiYongz();

    @Query(value = " select sum(price) from coupon where status=1", nativeQuery = true)
    double countweiShiYongz();

    @Query(value = " select sum(price) from coupon where status=2", nativeQuery = true)
    double countyiGuoQiz();
}
