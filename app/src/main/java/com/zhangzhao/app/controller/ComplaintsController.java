package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Complaints",description = "投诉")
@RestController
@RequestMapping("/app/complaints")
public class ComplaintsController extends BaseService {

    @ApiOperation(value = "1 投诉申请", notes = "投诉", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/add/s/complaints")
    public StatusVoidVo complaints(@ApiParam(value = "内容", required = true) @RequestParam String content) {
        return complaintsService.addComplaints(content);
    }
}
