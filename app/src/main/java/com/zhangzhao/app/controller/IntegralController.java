package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.IntegralVo;
import com.zhangzhao.common.vo.StatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Integral",description = "积分记录")
@RestController
@RequestMapping("/app/integral")
public class IntegralController extends BaseService {

    @GetMapping("/integral/s/list")
    @ApiOperation(value = "1 积分记录列表", notes = "积分记录列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<IntegralVo> findAll(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                                        @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return integralService.findAll(page,pageSize);
    }
}
