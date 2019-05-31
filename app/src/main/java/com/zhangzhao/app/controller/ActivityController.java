package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.ActivityVo;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Activity",description = "活动")
@RestController
@RequestMapping("/app/activity")
public class ActivityController extends BaseService {

    @GetMapping("/s/activity/list")
    @ApiOperation(value = "1 活动商品列表", notes = "活动商品列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<ActivityVo> findAll(){
        return activityService.findAlls();
    }

    @GetMapping("/s/activity/details/id")
    @ApiOperation(value = "2 活动详情", notes = "活动详情",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<ActivityVo> details(@ApiParam(value = "活动id", required = true) @RequestParam Long id){
        return activityService.details(id);
    }
}
