package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.StoreVo;
import com.zhangzhao.common.vo.StatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Store", description = "门店")
@RestController
@RequestMapping("/app/store")
public class StoreController extends BaseService {
    @GetMapping("/q/select/store")
    @ApiOperation(value = "1 门店列表", notes = "门店列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<StoreVo> findById() {
        return storeService.findAlls();
    }
}
