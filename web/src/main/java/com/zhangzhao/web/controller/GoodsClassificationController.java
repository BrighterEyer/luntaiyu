package com.zhangzhao.web.controller;

import com.zhangzhao.common.dto.GoodsClassificationDto;
import com.zhangzhao.common.entity.GoodsClassification;
import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Api(tags = "GoodsClassification", description = "商品分类")
@Controller
@RequestMapping("/web/goodsClassification")
public class GoodsClassificationController extends BaseService {

    @GetMapping("/find/category/s/view")
    @ApiOperation(value = "分类页面",position = 2, notes = "分类页面")
    public Object categoryView() {
        return "category/category-list";
    }

    @ResponseBody
    @GetMapping("/find/category/s/list")
    @ApiOperation(value = "分类列表", notes = "分类列表")
    public Object categorys(@RequestParam(required = false,defaultValue = "1")Integer page,
                            @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                            @RequestParam(required = false)Integer type,
                            @RequestParam(required = false,defaultValue = "0")Long id) {
        return goodsClassificationService.findAll(page,pageSize,type,id).toString();
    }

    @GetMapping("/category/edit/s/id")
    @ApiOperation(value = "轮播图修改页面",position = 2, notes = "轮播图修改页面")
    public Object edit(@ApiParam("分类id") @RequestParam(required = false) Long id, ModelMap model) {
        if (id != null) {
            Optional<GoodsClassification> optional = goodsClassificationService.findById(id);
            model.put("data", optional.orElse(null));
        }
        model.put("categorys", goodsClassificationService.findAlls());
        return "category/category-edit";
    }

    @ResponseBody
    @PostMapping("/category/save/s/id")
    @ApiOperation(value = "分类保存更新",position = 3, notes = "分类保存更新")
    public Object categorySave(@ApiParam("参数封装") @Valid GoodsClassificationDto goodsClassificationDto, BindingResult results) {
        return goodsClassificationService.saveB(goodsClassificationDto, results).toString();
    }

    @ResponseBody
    @PostMapping("/category/del/s/id")
    @ApiOperation(value = "删除分类",position = 4, notes = "删除分类")
    public Object delCategory(@ApiParam("分类id") @RequestParam long id) {
        return goodsClassificationService.del(id).toString();
    }
}
