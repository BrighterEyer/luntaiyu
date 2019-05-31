package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.BankCardVo;
import com.zhangzhao.common.dto.BankCardDto;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "BankCard", description = "银行卡")
@RestController
@RequestMapping("/app/bankcard")
@CrossOrigin
public class BankCardController extends BaseService {

    @ApiOperation(value = "1 新增/修改银行卡信息", notes = "新增银行卡信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/q/save/bankcard/id")
    public StatusVoidVo saveBankcard(@ApiParam(value = "请求参数封装") @RequestBody @Valid BankCardDto bankCardDto, BindingResult bindingResult) {
        return bankCardService.saveOrUp(bankCardDto, bindingResult);
    }

    @ApiOperation(value = "2 我的银行卡", notes = "我的银行卡", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/q/find/bankcard/all")
    public StatusVo<BankCardVo> bankcardAll() {
        return bankCardService.bankcardAll(getUser().getId());
    }

    @GetMapping("/bankcard/s/list")
    @ApiOperation(value = "3 银行卡列表", notes = "银行卡列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<BankCardVo> findAll(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                            @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return  bankCardService.findAll(page,pageSize);
    }
}
