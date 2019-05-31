package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.ForRecordVo;
import com.zhangzhao.app.vo.PresentDetailsVo;
import com.zhangzhao.app.vo.PresentWodeVo;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Present",description = "礼品")
@RestController
@RequestMapping("/app/present")
public class PresentController extends BaseService {

    @GetMapping("/present/s/list")
    @ApiOperation(value = "1 礼品列表", notes = "礼品列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<PresentWodeVo> findAll(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                                              @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return presentService.findAll(page,pageSize);
    }

    @GetMapping("/present/s/id")
    @ApiOperation(value = "2 礼品详情", notes = "礼品详情",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<PresentDetailsVo> findById(@ApiParam(value="礼品id")@RequestParam long id){
        return presentService.findById(id);
    }

    @GetMapping("/present/exchange/s/id")
    @ApiOperation(value = "3 礼品兑换", notes = "礼品兑换",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo exchange(@ApiParam(value="礼品id")@RequestParam long id, @ApiParam(value="收货id")@RequestParam long addressId){
        return presentService.exchange(id,addressId);
    }

    @GetMapping("/present/exchange/s/list")
    @ApiOperation(value = "4 兑换记录", notes = "兑换记录",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<ForRecordVo> exchangeList(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                                              @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return presentService.exchangeList(page,pageSize);
    }

    @GetMapping("/present/exchange/s/detail")
    @ApiOperation(value = "5 兑换详情", notes = "兑换详情",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<ForRecordVo> exchangeDetail(@ApiParam(value="记录id")@RequestParam long id){
        return presentService.exchangeDetail(id);
    }
}
