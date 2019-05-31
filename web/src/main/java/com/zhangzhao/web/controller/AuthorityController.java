package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Authority",description = "权限")
@Controller
@RequestMapping("/web/authority")
@CrossOrigin
public class AuthorityController extends BaseService {

    @ResponseBody
    @GetMapping("/find/authority/s/list")
    @ApiOperation(value = "权限",position = 2, notes = "权限")
    public Object findAll() {
        return authorityService.findAlls().toString();
    }
}
