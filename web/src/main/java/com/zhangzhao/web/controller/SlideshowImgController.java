package com.zhangzhao.web.controller;

import com.google.common.collect.Lists;
import com.zhangzhao.common.dto.SlideshowImgDto;
import com.zhangzhao.common.entity.SlideshowImg;
import com.zhangzhao.common.util.FileTool;
import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.*;

@Api(tags = "SlideshowImg",description = "轮播图")
@Controller
@RequestMapping("/web/slideshowimg")
public class SlideshowImgController extends BaseService {

    @ResponseBody
    @GetMapping("/find/slideshowimg/s/list")
    @ApiOperation(value = "轮播图管理", notes = "提轮播图管理")
    public Object slideshowimgs(@RequestParam(required = false,defaultValue = "1")Integer page,
                                @RequestParam(required = false,defaultValue = "10")Integer pageSize) {
        return slideshowImgService.findAll(page,pageSize).toString();
    }

    @GetMapping("/find/slideshowimg/s/view")
    @ApiOperation(value = "轮播图页面",position = 2, notes = "轮播图页面")
    public Object slideshowimgView() {
        return "slideshowimg/slideshowimg-list";
    }

    @GetMapping("/slideshowimg/edit/s/id")
    @ApiOperation(value = "轮播图修改页面",position = 3, notes = "轮播图修改页面")
    public Object edit(@ApiParam("图片id") @RequestParam(required = false) Long id, ModelMap model) {
        if (id != null) {
            Optional<SlideshowImg> optional = slideshowImgService.findById(id);
            model.put("data", optional.orElse(null));
        }
        return "slideshowimg/edit";
    }

    @ResponseBody
    @PostMapping("/slideshowimg/save/s/id")
    @ApiOperation(value = "轮播图保存更新",position = 3, notes = "轮播图保存更新")
    public Object adminSave(@ApiParam("参数封装") @Valid SlideshowImgDto slideshowImgDto, BindingResult results) {
        return slideshowImgService.saveImg(slideshowImgDto, results).toString();
    }

    @ResponseBody
    @PostMapping("/slideshowimg/del/s/id")
    @ApiOperation(value = "删除轮播图",position = 4, notes = "删除轮播图")
    public Object delAdmin(@ApiParam("轮播图id") @RequestParam long id) {
        return slideshowImgService.imgDel(id).toString();
    }

    @ResponseBody
    @PostMapping(value = "/img/s/upload",consumes="multipart/*",headers="content-type=multipart/form-data")
    @ApiOperation(value = "上传接口", notes = "上传图片功能")
    public List upload(@ApiParam(value="上传文件")HttpServletRequest request) throws Exception {
        List<String> list = Lists.newArrayList();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile multipartFile = multiRequest.getFile(iter.next());
                if(!"".equals(multipartFile) && multipartFile.getSize()>0){
                    InputStream ins = multipartFile.getInputStream();
                    String fileNames = FileTool.upload(ins, multipartFile.getOriginalFilename(),uploadConfig.getHouseFileUploadPath());
                    list.add(uploadConfig.getHouseFileSavePath()+"/"+fileNames);
                }
            }
        }
        return list;
    }
}
