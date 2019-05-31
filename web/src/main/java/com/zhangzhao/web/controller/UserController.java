package com.zhangzhao.web.controller;

import com.zhangzhao.common.dto.UserDto;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "User", description = "用户")
@Controller
@RequestMapping("/web/user")
public class UserController extends BaseService {

    @GetMapping("/statistics/list")
    @ApiOperation(value = "商家财务报表", notes = "商家财务报表")
    public String orderList(ModelMap model) {
        model.put("order", userService.statistics());
        return "statistics/list";
    }

    @GetMapping("/find/master/q/view")
    @ApiOperation(value = "师傅信息管理", notes = "师傅信息管理")
    public Object slideshowimgView() {
        return "user/master";
    }

    @GetMapping("/find/set/q/view")
    @ApiOperation(value = "师傅信息管理", notes = "师傅信息管理")
    public Object set() {
        return "user/list";
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "师傅列表", notes = "师傅列表")
    public Object masterList(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return userService.masterList(page, limit).toString();
    }

    @ResponseBody
    @GetMapping("/list2")
    @ApiOperation(value = "师傅列表2", notes = "师傅列表2")
    public Object masterList2(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return userService.masterList(page, limit).toString();
    }

    @ResponseBody
    @PostMapping("/del")
    @ApiOperation(value = "删除", notes = "删除")
    public Object delMaster(@ApiParam("id") @RequestParam Long id) {
        return userService.delMaster(id).toString();
    }

    @ResponseBody
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    public Object masterUpdate(@ApiParam("参数封装") @Valid UserDto userDto, BindingResult results) {

        return userService.masterUpdate(userDto, results).toString();
    }

    @ResponseBody
    @PostMapping("/update2")
    @ApiOperation(value = "修改", notes = "修改")
    public Object masterSetUpdate(@ApiParam("参数封装") @Valid UserDto userDto, BindingResult results) {

        return userService.masterUpdate(userDto, results).toString();
    }

    @ResponseBody
    @PostMapping("/save")
    @ApiOperation(value = "添加", notes = "添加")
    public Object masterSave(@ApiParam("参数封装") @Valid UserDto userDto, BindingResult results) {
        return userService.masterSave(userDto, results).toString();
    }

    @ResponseBody
    @PostMapping("/updateAudit")
    @ApiOperation(value = "审核", notes = "审核")
    public StatusVoidVo updateAudit(Long id, int applyStatus) {
        return userService.updateAudit(id, applyStatus);
    }

}
