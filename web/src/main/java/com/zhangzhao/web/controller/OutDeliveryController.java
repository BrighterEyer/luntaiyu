package com.zhangzhao.web.controller;

import com.zhangzhao.common.dto.OutDeliveryDto;
import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "OutDelivery", description = "出库信息")
@Controller
@RequestMapping("/web/OutDelivery")
@CrossOrigin
public class OutDeliveryController extends BaseService {

    @GetMapping("/repertory/q/view")
    @ApiOperation(value = "库存查询页面", notes = "库存查询页面")
    public Object repertory() {
        return "repertory/list";
    }

    @GetMapping("/repertory/q/outinventory")
    @ApiOperation(value = "出库页面", notes = "出库页面")
    public Object outinventory() {
        return "repertory/outinventory";
    }

    @ResponseBody
    @GetMapping("/repertory/list")
    @ApiOperation(value = "库存列表", notes = "库存列表")
    public Object repertoryList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false) String name) {
        return outDeliveryService.findAll(page, pageSize, name).toString();
    }


    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "出库列表", notes = "出库列表")
    public Object outinventoryList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return outDeliveryService.findAll(page, pageSize).toString();
    }

    @ResponseBody
    @PostMapping("/out")
    @ApiOperation(value = "出库", notes = "出库")
    public Object outinventory(@ApiParam("参数封装") @Valid OutDeliveryDto outDeliveryDto, BindingResult results) {
        return outDeliveryService.outinventory(outDeliveryDto, results).toString();
    }

    @ResponseBody
    @PostMapping("/repertory/update")
    @ApiOperation(value = "修改库存", notes = "修改库存")
    public Object couponUpdate(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) int inventory) {
        return outDeliveryService.couponUpdate(id, inventory).toString();
    }
}
