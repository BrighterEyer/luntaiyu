package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.PrecontractEvaluateService;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.PrecontractEvaluateDto;
import com.zhangzhao.common.entity.PrecontractEvaluate;
import com.zhangzhao.common.vo.StatusOneVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

/**
 * 预约评价
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PrecontractEvaluateServiceImpl extends BaseService implements PrecontractEvaluateService {


    @Override
    public StatusOneVo save(PrecontractEvaluateDto precontractEvaluateDto, BindingResult result) {
        StatusOneVo vo = new StatusOneVo();
        if (result.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        PrecontractEvaluate evaluate = precontractEvaluateMapper.dtoToBean(precontractEvaluateDto);
        evaluate.setUserId(getUser().getId());
        evaluate.setContent(precontractEvaluateDto.getContent());
        evaluate.setImg(precontractEvaluateDto.getImg());
        evaluate.setStatus(precontractEvaluateDto.getStatus());
        evaluate.setType(precontractEvaluateDto.getType());
        PrecontractEvaluate precontractEvaluate = precontractEvaluateRepository.saveAndFlush(evaluate);
        vo.success(precontractEvaluate.getId());
        return vo;
    }
}
