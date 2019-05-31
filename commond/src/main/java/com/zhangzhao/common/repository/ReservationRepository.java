package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 预约订单
 *
 * @author Administrator
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update reservation set province=:province,district=:district,city=:city,detailedAddress=:detailedAddress where id=:id", nativeQuery = true)
    void updateAddress(@Param("id") Long id, @Param("province") String province, @Param("district") String district, @Param("city") String city, @Param("detailedAddress") String detailedAddress);

    @Modifying(clearAutomatically = true)
    @Query(value = " update reservation set status=:status where  id=:id", nativeQuery = true)
    void updateStatus(@Param("id") Long id, @Param("status") int status);

    @Modifying(clearAutomatically = true)
    @Query(value = " update reservation set statusOk=:statusOk where  id=:id", nativeQuery = true)
    void updatestatusOk(@Param("id") Long id, @Param("statusOk") int statusOk);

    Reservation findByreservationNumber(String reservationNumber);

    Optional<Reservation> findById(Long id);

    List<Reservation> findByIdAndStatus(Long id, Integer[] status);

    Page<Reservation> findByUserIdAndYesnoShopnoteAndStatus(Long id, int type, int status, Pageable pageable);

    Page<Reservation> findByUserIdAndStatus(Long id, int status, Pageable pageable);

    Page<Reservation> findByUserIdAndType(Long id, int type, Pageable pageable);

    /**
     * 累计订单统计
     *
     * @return
     */
    @Query(value = " select count(*) from reservation where status=5 and id=:id", nativeQuery = true)
    int selectByStatus(Long id);

    /**
     * 日订单统计
     *
     * @return
     */
    @Query(value = "select count(*) from reservation where status=5 and DAY(finish_time)=DAY(NOW()) and id=:id", nativeQuery = true)
    int selectAllocateTimeDays(Long id);

    /**
     * 累计收支
     *
     * @return
     */
    @Query(value = "select SUM(income_price) from reservation WHERE status=5 and id=:id", nativeQuery = true)
    double selectAddShouzhi(Long id);

    /**
     * 昨日收支
     *
     * @return
     */
    @Query(value = "SELECT SUM(income_price) FROM reservation WHERE allocate_time >= CURDATE() AND allocate_time < DATE_SUB(CURDATE(),INTERVAL -1 DAY) and id=:id", nativeQuery = true)
    double selectyesterdayShouzhi(Long id);


    /**
     * 好评数
     *
     * @return
     */
    @Query(value = "select count(*) from reservation where status=5 and star>=3 and id=:id", nativeQuery = true)
    int countStars(Long id);

    /**
     * 准时数
     *
     * @return
     */
    @Query(value = "SELECT count(*) from reservation where status=5 and finish_time<=appointment_time and id=:id", nativeQuery = true)
    int zhunShiShu(Long id);

    /**
     * 当前排名
     *
     * @param masterId
     * @return
     */
    @Query(value = "select (select count(*) from reservation where r.hao_pinglv<=hao_pinglv or (r.hao_pinglv=hao_pinglv and r.finish_time<finish_time)) as 排名 from reservation r where master_id=:masterId", nativeQuery = true)
    int preantranking(@Param("masterId") Long masterId);

    /**
     * 总订单数
     *
     * @param id
     * @return
     */
    @Query(value = " select sum(*) from reservation where status=5 and id=:id", nativeQuery = true)
    int count(Long id);


}
