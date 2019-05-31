package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.GoodsClassificationDto;
import com.zhangzhao.common.entity.GoodsClassification;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.vo.GoodsClassificationVo;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface GoodsClassificationService extends CommonService {

    StatusPageVo<GoodsClassificationVo> findAll(Integer page, Integer pageSize,Integer type,Long id);

    Optional<GoodsClassification> findById(Long id);

    StatusVoidVo saveB(GoodsClassificationDto goodsClassificationDto, BindingResult results);

    StatusVoidVo del(Long id);
}
