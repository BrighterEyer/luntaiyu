package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.OrderSupplyDto;
import com.zhangzhao.common.dto.WebOrderDto;
import com.zhangzhao.common.entity.OrderSupply;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.validation.BindingResult;

public interface OrderSupplyService extends CommonService {

    StatusPageVo findAll(Integer page, Integer pageSize,String keyword,Integer status);

    OrderSupply findById(Long id);

    StatusVoidVo saveOrder(WebOrderDto webOrderDto, BindingResult bindingResult);

    StatusVoidVo updateStatus(Long id, int status);
}
