package com.zhangzhao.web.controller;

import com.zhangzhao.common.dto.AdminDto;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "Admin",description = "后台管理")
@Controller
@RequestMapping("/web/admin")
@CrossOrigin
public class AdminController extends BaseService {

    @GetMapping("/s/index")
    @ApiOperation(value = "首页",position = 5, notes = "首页")
    public String index(){
        return "index";
    }

    @GetMapping("/s/logout")
    @ApiOperation(value = "退出登入",position = 5, notes = "退出登入")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @ResponseBody
    @PostMapping("/update/password/s/id")
    @ApiOperation(value = "修改密码",position = 3, notes = "修改密码")
    public StatusVoidVo updatePassword(@ApiParam("新密码")@RequestParam String newPassword){
        StatusVoidVo vo = new StatusVoidVo();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newPassword = encoder.encode(newPassword);
        adminService.updatePassword(getAdmin().getId(), newPassword);
        vo.success();
        return vo;
    }

    @ResponseBody
    @GetMapping("/children/admin/s/list")
    @ApiOperation(value = "子账户管理", notes = "子账户管理")
    public Object childrens(@RequestParam(required = false,defaultValue = "1")Integer page,
                            @RequestParam(required = false,defaultValue = "10")Integer limit) {
        return adminService.childrens(page,limit).toString();
    }

    @GetMapping("/children/admin/s/view")
    @ApiOperation(value = "子账户管理",position = 2, notes = "子账户管理")
    public Object childrenView() {
        return "admin/childrens";
    }

    @ResponseBody
    @PostMapping("/children/admin/auth/s/save")
    @ApiOperation(value = "子账户权限保存",position = 3, notes = "子账户权限保存")
    public Object authSave(@ApiParam("子账户id") @RequestParam Long id,
                           @ApiParam("用户名") @RequestParam String name,
                           @ApiParam("密码") @RequestParam(required = false) String password,
                           @ApiParam("权限") @RequestParam(required = false) List<Long> auths) {
        return adminService.authSave(id,name,password,auths).toString();
    }

    @ResponseBody
    @PostMapping("/children/admin/s/save")
    @ApiOperation(value = "子账户保存",position = 1, notes = "子账户保存")
    public Object adminSave(@ApiParam("参数封装")@Valid AdminDto adminDto, BindingResult results) {
        return adminService.adminSave(adminDto,results).toString();
    }

    @ResponseBody
    @PostMapping("/children/admin/s/del")
    @ApiOperation(value = "删除子账户",position = 4, notes = "删除子账户")
    public Object delAdmin(@ApiParam("子账户id") @RequestParam Long id) {
        return adminService.delAdmin(id).toString();
    }
}
