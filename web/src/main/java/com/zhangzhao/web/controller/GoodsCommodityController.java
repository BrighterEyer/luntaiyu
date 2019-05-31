package com.zhangzhao.web.controller;

import com.zhangzhao.common.dto.GoodsCommodityDto;
import com.zhangzhao.common.dto.GoodsCommodityUpdateDto;
import com.zhangzhao.common.entity.GoodSecurity;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.Properties;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.vo.GoodsClassificationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Api(tags = "GoodsCommodity", description = "商品信息")
@Controller
@RequestMapping("/web/goodsCommodity")
@CrossOrigin
public class GoodsCommodityController extends BaseService {
    @GetMapping("/find/goodsCommodity/s/view")
    @ApiOperation(value = "商品页面",position = 2, notes = "商品页面")
    public Object goodsView() {
        return "good/good-list";
    }

    @ResponseBody
    @GetMapping("/find/goodsCommodity/s/list")
    @ApiOperation(value = "商品列表", notes = "商品列表")
    public Object goods(@RequestParam(required = false, defaultValue = "1") Integer page,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) String keyword,
                        @RequestParam(required = false, defaultValue = "0") Integer status) {
        return goodsCommodityService.findAll(page, pageSize, keyword, status).toString();
    }

    @GetMapping("/find/goodsCommodity/edit/s/view")
    @ApiOperation(value = "商品修改页面",position = 2, notes = "商品修改页面")
    public Object edit(ModelMap model, @RequestParam(required = false) Long id) {
        List<Properties> list = propertiesService.findAll();
        List<GoodSecurity> securityList = goodSecurityService.findAll();
        StatusPageVo<GoodsClassificationVo> all = goodsClassificationService.findAll(1, 100, id == null ? 0 : null, 0L);
        model.put("securityList", securityList);
        model.put("lists", list);
        model.put("categorys", all.getData());
        if (id != null) {
            Optional<GoodsCommodity> optional = goodsCommodityService.findById(id);
            model.put("data", optional.get());
            return "good/goods-update";
        }
        return "good/goods-edit";
    }

    @ResponseBody
    @PostMapping(value = "/goodsCommodity/save/s/id", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "商品添加",position = 1, notes = "商品添加")
    public Object save(@ApiParam("参数封装") @RequestBody GoodsCommodityDto goodsCommodityDto, BindingResult results) {
        return goodsCommodityService.save(goodsCommodityDto, results).toString();
    }

    @ResponseBody
    @PostMapping("/goodsCommodity/s/del/id")
    @ApiOperation(value = "删除商品",position = 4, notes = "删除商品")
    public Object delAdmin(@ApiParam("商品id") @RequestParam Long id,
                           @RequestParam(required = false, defaultValue = "0") Integer status) {
        return goodsCommodityService.delById(id, status).toString();
    }

    @ResponseBody
    @PostMapping(value = "/goodsCommodity/update/save/s/id", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "商品修改",position = 3, notes = "商品修改")
    public Object update(@ApiParam("参数封装") @Validated @RequestBody GoodsCommodityUpdateDto goodsCommodityUpdateDto, BindingResult results) {
        return goodsCommodityService.save(goodsCommodityUpdateDto, results).toString();
    }

    @ResponseBody
    @GetMapping("/export/report/s/list")
    @ApiOperation(value = "商品excle导出", notes = "商品excle导出")
    public void export(@ApiParam("开始时间") @RequestParam String startTime,
                       @ApiParam("结束时间") @RequestParam String endTime, HttpServletResponse response) {
        HSSFWorkbook wb = goodsCommodityService.export(startTime, endTime, response);
        try {
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("报表.xls".getBytes(), "ISO8859-1"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/find/property/s/view")
    @ApiOperation(value = "商品属性页面",position = 2, notes = "商品属性页面")
    public Object propertyView() {
        return "good/property-list";
    }

    @ResponseBody
    @GetMapping("/find/property/s/list")
    @ApiOperation(value = "商品属性列表", notes = "商品属性列表")
    public Object property(@RequestParam(required = false, defaultValue = "1") Integer page,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) Integer type) {
        return goodsCommodityService.findAll(page, pageSize, type).toString();
    }

    @ResponseBody
    @PostMapping("/find/property/s/save")
    @ApiOperation(value = "商品属性保存",position = 1, notes = "商品属性保存")
    public Object save(@ApiParam(value = "json", required = true) Properties properties) {
        return goodsCommodityService.save(properties);
    }

    @ResponseBody
    @PostMapping("/property/del/s/id")
    @ApiOperation(value = "删除属性",position = 4, notes = "删除属性")
    public Object delCategory(@ApiParam("属性id") @RequestParam long id) {
        return goodsCommodityService.delProperties(id).toString();
    }


}
