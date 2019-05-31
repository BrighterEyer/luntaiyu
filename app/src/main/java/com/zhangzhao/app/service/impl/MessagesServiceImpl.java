package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ActivityService;
import com.zhangzhao.app.service.MessagesService;
import com.zhangzhao.app.vo.MessagesVo;
import com.zhangzhao.common.entity.Messages;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 消息
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessagesServiceImpl extends BaseService implements MessagesService {

    @Override
    public StatusVo<MessagesVo> findAll(Integer page, Integer pageSize, String... p) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "createTime");
        Page<Messages> all = messagesRepository.findByUserId(getUser().getId(), pageable);
        List<MessagesVo> collect = all.getContent().parallelStream().map(messagesMapper::beanToVo).collect(Collectors.toList());
        StatusVo<MessagesVo> vo=new StatusVo<>();
        vo.success(collect);
        return vo;
    }
    /**
     *  用户信息条数
     */
    @Override
    public StatusOneVo articleNumber(Long id) {
        StatusOneVo vo = new StatusOneVo();
        int information = messagesRepository.selectByStatus(id);
        vo.success(information);
        return vo;
    }
}
