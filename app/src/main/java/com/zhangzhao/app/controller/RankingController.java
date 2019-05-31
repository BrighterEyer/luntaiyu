package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.common.vo.StatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Ranking", description = "排行")
@RestController
@RequestMapping("/app/ranking")
public class RankingController extends BaseService {

    @GetMapping("/q/ranking")
    @ApiOperation(value = "排名", notes = "排名")
    public StatusVo<String> ranking(@ApiParam(value = "类型 1月 2季度 3年") @RequestParam(required = false) int type) {
        return rankingService.ranking(type);
    }

}
