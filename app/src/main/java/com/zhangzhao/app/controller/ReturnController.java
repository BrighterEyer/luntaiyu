package com.zhangzhao.app.controller;

import com.zhangzhao.app.vo.OrderDetailsVo;
import com.zhangzhao.app.vo.OrderSupplyVo;
import com.zhangzhao.app.vo.ReturnPolicyVo;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

import java.util.List;

@Api(tags = "Return", description = "退换货")
@RestController
@RequestMapping("/app/return")
public class ReturnController extends BaseService {

    @GetMapping("/return/s/refund")
    @ApiOperation(value = "1 退款前", notes = "退款前", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<OrderSupplyVo> refund(@ApiParam(value = "订单id") @RequestParam Long id){
        return returnPolicyService.refund(id);
    }

    @PostMapping("/return/s/refund/per")
    @ApiOperation(value = "2 退款提交", notes = "退款提交", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo refundPer(@ApiParam(value = "订单id") @RequestParam Long id,
                                  @ApiParam(value = "原因") @RequestParam String why,
                                  @ApiParam(value = "说明") @RequestParam(required = false) String instructions){
        return returnPolicyService.refundPer(id,why,instructions);
    }

    @GetMapping("/return/s/after/sale")
    @ApiOperation(value = "3 申请售后前", notes = "申请售后前", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<OrderDetailsVo> afterSale(@ApiParam(value = "订单详情id集合") @RequestParam List<Long> ids,
                                                 @ApiParam(value = "订单id") @RequestParam Long id){
        return returnPolicyService.afterSale(ids);
    }

    @PostMapping("/return/s/after/sale/per")
    @ApiOperation(value = "4 售后提交", notes = "售后提交", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo salePer(@ApiParam(value = "订单详情id集合") @RequestParam List<Long> ids,
                                @ApiParam(value = "订单id") @RequestParam Long id,
                                @ApiParam(value = "原因") @RequestParam(required = false) String why,
                                @ApiParam(value = "说明") @RequestParam(required = false) String instructions,
                                @ApiParam(value = "图片") @RequestParam(required = false) String img,
                                @ApiParam(value = "类型 2-补发 3-退货退款") @RequestParam int type){
        return returnPolicyService.salePer(ids,id,why,instructions,img,type);
    }

    @GetMapping("/return/s/sale/list")
    @ApiOperation(value = "5 退款/售后列表", notes = "退款/售后列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<ReturnPolicyVo> saleList(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                                             @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return returnPolicyService.saleList(page,pageSize);
    }

    @GetMapping("/return/s/sale/detail")
    @ApiOperation(value = "6 退款/售后详情", notes = "退款/售后详情", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<ReturnPolicyVo> saleDetail(@ApiParam(value="售后id")@RequestParam Long id){
        return returnPolicyService.saleDetail(id);
    }

    @PostMapping("/return/s/after/reissue/per")
    @ApiOperation(value = "7 补发确认收货", notes = "补发确认收货", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo reissuePer(@ApiParam(value = "售后id") @RequestParam Long id,
                                @ApiParam(value = "状态 3-补发收货") @RequestParam int status){
        return returnPolicyService.reissuePer(id,status);
    }

    @PostMapping("/return/s/after/reissue/number")
    @ApiOperation(value = "8 添加退货物流", notes = "添加退货物流", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo reissueNumber(@ApiParam(value = "售后id") @RequestParam Long id,
                                @ApiParam(value = "物流单号")  @RequestParam String number,@ApiParam(value = "退货名称")@RequestParam String reissueNumber){
        return returnPolicyService.reissueNumber(id,number,reissueNumber);
    }
}
