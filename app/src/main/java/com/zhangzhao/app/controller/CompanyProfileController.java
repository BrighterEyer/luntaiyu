package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.CompanyProfileVo;
import com.zhangzhao.common.vo.StatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "CompanyProfile", description = "热门搜索词")
@RestController
@RequestMapping("/app/companyprofile")
public class CompanyProfileController extends BaseService {

    @GetMapping("/s/company/profile/list")
    @ApiOperation(value = "1 热门词", notes = "热门词",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<CompanyProfileVo> findAll(){
        return companyProfileService.findAlls();
    }

    @GetMapping("/s/company/profile/city/list")
    @ApiOperation(value = "2 热门城市", notes = "热门城市",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<CompanyProfileVo> citys(){
        return companyProfileService.citys();
    }

    @GetMapping("/s/company/profile/city/grouping")
    @ApiOperation(value = "3 城市列表", notes = "城市列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<CompanyProfileVo> grouping(){
        return companyProfileService.crtyList();
    }
    //模糊匹配
    @GetMapping("/s/company/profile/city/blurry/")
    @ApiOperation(value = "4 模糊匹配城市名", notes = "模糊匹配城市名",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<CompanyProfileVo> blurry(String city){
        return companyProfileService.findByLikeList(city);
    }
}
