package com.zhangzhao.app.controller;

import com.zhangzhao.app.vo.GoodsClassificationVo;
import com.zhangzhao.common.vo.StatusVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "GoodsClassification", description = "商品分类")
@RestController
@RequestMapping("/app/goodsClassification")
public class GoodsClassificationController extends BaseService {

    @GetMapping("/s/goods/classification/list")
    @ApiOperation(value = "1 首页商品分类", notes = "首页商品分类",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<GoodsClassificationVo> findAll(@ApiParam(value = "不传返回所有一级，传0返回所有分类，传一级id返回当前一级分类和子分类")
                                                       @RequestParam(required = false) Long id){
        return goodsClassificationService.findAll(id);
    }
}
