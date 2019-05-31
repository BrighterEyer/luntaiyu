package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.common.dto.PrecontractEvaluateDto;
import com.zhangzhao.common.vo.StatusOneVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "PrecontractEvaluate", description = "预约评价")
@RestController
@RequestMapping("/app/precontractEvaluate")
public class PrecontractEvaluateController extends BaseService {

    @ApiOperation(value = "1 预约评论", notes = "预约评论", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/q/precontract/evaluate", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo saveBean(@ApiParam(value = "请求参数封装") @RequestBody @Valid PrecontractEvaluateDto precontractEvaluateDto, BindingResult result) {
        return precontractEvaluateService.save(precontractEvaluateDto, result);
    }

}
