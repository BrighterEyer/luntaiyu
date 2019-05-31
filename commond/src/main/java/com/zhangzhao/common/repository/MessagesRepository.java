package com.zhangzhao.common.repository;

import com.zhangzhao.common.entity.Messages;
import com.zhangzhao.common.vo.StatusOneVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 消息
 */
@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long>, JpaSpecificationExecutor<Messages> {

    Page<Messages> findByUserId(Long userId, Pageable pageable);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = " select count(message_content) from messages where user_id =?1", nativeQuery = true)
    int selectByStatus( Long id);
}
