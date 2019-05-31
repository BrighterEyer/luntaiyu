package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 排名
 */
@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long>, JpaSpecificationExecutor<Ranking> {


    /**
     * 月排行
     *
     * @return
     */
    @Query(value = "SELECT r.hao_pinglv,u.icon,u.name from reservation r LEFT JOIN user u on (u.id=r.master_id) WHERE DATE_FORMAT(finish_time,'%Y-%m') = DATE_FORMAT(NOW(),'%Y-%m') order by hao_pinglv desc", nativeQuery = true)
    List<String> monthRanking();

    /**
     * 季度排行
     *
     * @return
     */
    @Query(value = "SELECT r.hao_pinglv,u.icon,u.name FROM reservation r LEFT JOIN user u on (u.id=r.master_id) WHERE quarter( FROM_UNIXTIME( finish_time )) = quarter( CURDATE( )) order by hao_pinglv desc", nativeQuery = true)
    List<String> quarterRanking();

    /**
     * 年排名
     *
     * @return
     */
    @Query(value = "SELECT r.hao_pinglv,u.icon,u.name from reservation r LEFT JOIN user u on (u.id=r.master_id) WHERE DATE_FORMAT(finish_time,'%Y') = DATE_FORMAT(NOW(),'%Y') order by hao_pinglv desc", nativeQuery = true)
    List<String> yearRanking();
}
