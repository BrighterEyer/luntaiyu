package com.zhangzhao.app.controller;

import com.zhangzhao.app.vo.MemberVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "Member", description = "会员")
@RestController
@RequestMapping("/app/member")
public class MemberController extends BaseService {

    @PostMapping("/member/s/open")
    @ApiOperation(value = "1 钱包开通会员", notes = "钱包开通会员", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo openMember(@ApiParam(value = "类型 1-1月 2-3月 3-6月 4-12月", required = true) @RequestParam int type,
                                   @ApiParam(value = "金额", required = true) @RequestParam() double money) {
        return memberService.openMember(type, money);
    }

    @GetMapping("/member/s/list")
    @ApiOperation(value = "2 会员价格", notes = "会员价格", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<MemberVo> members(){
        return memberService.members();
    }

}
