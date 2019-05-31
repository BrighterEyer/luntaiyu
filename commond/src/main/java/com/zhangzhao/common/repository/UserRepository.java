package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户管理
 *
 * @author Administrator
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByName(String name);

    int findByMember(Long id);

    int findByGratisTime(Long id);

    User findByPhone(String phone);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.password = :password where u.id=:id", nativeQuery = true)
    void updatePassword(@Param("id") Long id, @Param("password") String password);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.phone = :phone where u.id=:id", nativeQuery = true)
    void updateBindPhone(@Param("id") Long id, @Param("phone") String phone);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.icon=:icon where u.id=:id", nativeQuery = true)
    void saveIcon(@Param("id") Long id, @Param("icon") String icon);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.use_type = :useType where u.id=:id", nativeQuery = true)
    void useType(@Param("id") Long id, @Param("useType") int useType);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.latitude = :latitude,u.longitude=:longitude,u.update_time=unix_timestamp(now()) where u.id=:id", nativeQuery = true)
    void updatejwdu(@Param("id") Long id, @Param("latitude") double latitude, @Param("longitude") double longitude);

    @Query(value = " select sum(payment_prcie) from order_supply where status=5", nativeQuery = true)
    double sumBypaymentPrcie();

    @Query(value = " select count(*) from order_supply where status=5", nativeQuery = true)
    int countByStatus();

    @Query(value = "SELECT sum(payment_prcie) FROM order_supply WHERE DATE_FORMAT(complete_time, '%Y') = DATE_FORMAT(NOW(), '%Y') and status=5", nativeQuery = true)
    double yearMoney();

    @Query(value = "select sum(payment_prcie) from order_supply where QUARTER(complete_time)=QUARTER(now()) and status=5", nativeQuery = true)
    double quarterMoney();

    @Query(value = "SELECT sum(payment_prcie) from order_supply  WHERE DATE_FORMAT(complete_time,'%Y-%m') = DATE_FORMAT(NOW(),'%Y-%m') AND status=5", nativeQuery = true)
    double monthMoney();

    Page<User> findByTypeOrApplyStatus(int type, int applyStatus, Pageable pageable);

    @Modifying
    @Query(value = "delete from user where id=?1", nativeQuery = true)
    void delMaster(Long id);

    @Modifying
    @Query(value = "update user a set a.apply_status=:applyStatus where a.id=:id", nativeQuery = true)
    void updateAudit(@Param("id") Long id, @Param("applyStatus") int applyStatus);

}
