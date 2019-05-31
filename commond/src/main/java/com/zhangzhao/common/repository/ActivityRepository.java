package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 活动LessThan
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {

    List<Activity> findByEndTimeAfter(Date date);

    Activity findByGoodIdAndStartTimeLessThanEqualAndEndTimeAfter(Long goodId,Date start,Date end);

    List<Activity> findByGoodIdInAndStartTimeLessThanEqualAndEndTimeAfter(List<Long> goods,Date start,Date end);
}
