package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.QualityService;
import com.zhangzhao.app.vo.QualityVo;
import com.zhangzhao.common.entity.Quality;
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
 * 质量处理
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QualityServiceImpl extends BaseService implements QualityService {

    @Override
    public StatusVoidVo saveProcessing(Long userId, String cause, String img, String explains) {
        StatusVoidVo vo = new StatusVoidVo();
        Quality quality = new Quality();
        quality.setUserId(userId);
        quality.setCause(cause);
        quality.setImg(img);
        quality.setStatus(Quality.Status.FOLLOW.getStatus());
        quality.setExplains(explains);
        qualityRepository.saveAndFlush(quality);
        vo.success();
        return vo;
    }

    /**
     * 质量列表
     *
     * @return
     */
    @Override
    public StatusVo findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "createTime");
        Page<Quality> all = qualityRepository.findByUserId(getUser().getId(), pageable);
        List<QualityVo> collect = all.stream().map(qualityMapper::beanToVo).collect(Collectors.toList());
        StatusVo vo = new StatusVo();
        vo.success(collect);
        return vo;
    }


    /**
     * 质量详情
     *
     * @return
     */
    @Override
    public StatusOneVo<QualityVo> findById(Long id) {
        StatusOneVo<QualityVo> vo = new StatusOneVo<>();
        Optional<Quality> optional = qualityRepository.findById(id);
        if (optional.isPresent()) {
            vo.success(qualityMapper.beanToVo(optional.get()));
        }
        return vo;
    }

}
