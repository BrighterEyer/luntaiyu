package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.AddressVo;
import com.zhangzhao.app.vo.MessagesVo;
import com.zhangzhao.common.dto.AddressDto;
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

@Api(tags = "address",description = "地址")
@RestController
@RequestMapping("/app/address")
public class AddressController extends BaseService {

    @GetMapping("/address/s/list")
    @ApiOperation(value = "1 用户地址列表", notes = "用户地址列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<AddressVo> findAll(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                                       @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return addressService.findAll(page,pageSize,null);
    }

    @ApiOperation(value = "2 添加/编辑地址", notes = "添加/编辑地址", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/address/s/save/or/up")
    public StatusVoidVo saveBankcard(@ApiParam(value = "请求参数封装") @RequestBody @Valid AddressDto addressDto, BindingResult bindingResult) {
        return addressService.saveOrUp(addressDto, bindingResult);
    }

    @ApiOperation(value = "3 删除地址", notes = "删除地址", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/address/s/del/id")
    public StatusVoidVo del(@ApiParam(value="地址id")@RequestParam Long id) {
        return addressService.del(id);
    }
}
