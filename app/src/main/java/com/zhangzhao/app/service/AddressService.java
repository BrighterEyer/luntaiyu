package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.AddressDto;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.validation.BindingResult;

public interface AddressService extends CommonService {

    StatusVoidVo saveOrUp(AddressDto addressDto, BindingResult bindingResult);

    StatusVoidVo del(Long id);
}
