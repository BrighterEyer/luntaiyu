package com.zhangzhao.web.controller;

import com.zhangzhao.common.dto.OrderSupplyDto;
import com.zhangzhao.common.dto.WebOrderDto;
import com.zhangzhao.common.entity.OrderSupply;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "OrderSupply",description = "订单")
@Controller
@RequestMapping("/web/ordersupply")
@CrossOrigin
public class OrderSupplyController extends BaseService {

    @GetMapping("/find/order/s/view")
    @ApiOperation(value = "订单页面",position = 2, notes = "订单页面")
    public Object orderView() {
        return "order/order-list";
    }

    @ResponseBody
    @GetMapping("/find/order/s/list")
    @ApiOperation(value = "订单列表", notes = "订单列表")
    public Object orderList(@RequestParam(required = false,defaultValue = "1")Integer page,
                        @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                        @RequestParam(required = false) String keyword,
                        @RequestParam(required = false)Integer status) {
        return orderSupplyService.findAll(page,pageSize,keyword,status).toString();
    }

    @GetMapping("/order/update/s/id")
    @ApiOperation(value = "订单修改页面",position = 2, notes = "订单修改页面")
    public Object updateView(@RequestParam Long id, ModelMap model) {
        OrderSupply supply = orderSupplyService.findById(id);
        model.put("supply",supply);
        model.put("statusName",statusToString(supply.getStatus()));
        return "order/order-update";
    }

    @ResponseBody
    @PostMapping("/order/s/update/id")
    @ApiOperation(value = "修改订单",position = 3, notes = "修改订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo updateOrder(@ApiParam(value = "请求参数封装") @RequestBody @Valid WebOrderDto webOrderDto, BindingResult bindingResult) {
        return orderSupplyService.saveOrder(webOrderDto, bindingResult);
    }

    @ApiOperation(value = "订单状态改变",position = 3, notes = "订单状态改变", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/order/s/cancel/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo delCancel(@ApiParam(value = "订单id", required = true) @RequestParam Long id,
                                  @ApiParam(value = "状态 -2取消",required = true) @RequestParam int type) {
        return orderSupplyService.updateStatus(id, type);
    }

    public static String statusToString(int status){
        String statusName="";
        switch (status){
            case -2:
                statusName="取消";
                break;
            case -1:
                statusName="已删除";
                break;
            case 1:
                statusName="待付款";
                break;
            case 2:
                statusName="待发货";
                break;
            case 3:
                statusName="待收货";
                break;
            case 4:
                statusName="待评价";
                break;
             case 5:
                statusName="已完成";
                break;
            case 6:
                statusName="已退款";
                break;
            case 7:
                statusName="已退货退款";
                break;
        }
        return statusName;
    }
}
