package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.PrecontractEvaluateDto;
import com.zhangzhao.common.vo.StatusOneVo;
import org.springframework.validation.BindingResult;

public interface PrecontractEvaluateService extends CommonService {


    StatusOneVo save(PrecontractEvaluateDto precontractEvaluateDto, BindingResult result);

}
