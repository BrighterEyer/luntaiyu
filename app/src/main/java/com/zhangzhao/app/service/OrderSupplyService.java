package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.OrderDetailsVo;
import com.zhangzhao.app.vo.OrderSupplyVo;
import com.zhangzhao.app.vo.PlaceOrderVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.OrderSupplyDto;
import com.zhangzhao.common.entity.OrderSupply;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OrderSupplyService extends CommonService {

    StatusOneVo<PlaceOrderVo> placeOrder(Long id, String installationType, Integer amount, List<Long> ids);

    StatusOneVo saveOrder(OrderSupplyDto orderSupplyDto, BindingResult bindingResult);

    OrderSupply findById(Long id);

    OrderSupply findOrderByNumber(String orderNumber);

    StatusVoidVo updateStatus(Long id, int status);

    StatusVo<OrderSupplyVo> findOrder(Long userId);

    StatusVo<OrderDetailsVo> againOrder(Long orderId);
}
