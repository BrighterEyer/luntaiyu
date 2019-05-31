package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.MainVo;
import com.zhangzhao.app.vo.SlideshowImgVo;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "SlideshowImg",description = "轮播图")
@RestController
@RequestMapping("/app/slideshowimg")
public class SlideshowImgController extends BaseService {

    @GetMapping("/s/slideshow/img/list")
    @ApiOperation(value = "1 首页", notes = "首页",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<MainVo> slideshowImgList(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                                                @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "8")Integer pageSize){
        return slideshowImgService.slideshowImgList(page,pageSize);
    }
}
