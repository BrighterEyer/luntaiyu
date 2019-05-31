package com.zhangzhao.app.controller;

import com.zhangzhao.app.vo.MasterTimeVo;
import com.zhangzhao.common.vo.StatusOneVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "MasterTime", description = "公共参数")
@RestController
@RequestMapping("/app/mastertime")
public class MasterTimeController extends BaseService {

    @GetMapping("/s/master/time/query/common")
    @ApiOperation(value = "1 公共参数", notes = "公共参数", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<MasterTimeVo> queryCommon(){
        return masterTimeService.queryCommon();
    }
}
