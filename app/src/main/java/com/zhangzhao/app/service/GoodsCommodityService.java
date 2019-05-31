package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.CompanyProfileVo;
import com.zhangzhao.app.vo.GoodsCommodityDetailsVo;
import com.zhangzhao.app.vo.GoodsCommodityVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;

import java.util.List;

public interface GoodsCommodityService extends CommonService {

    StatusVo<GoodsCommodityVo> findNoyesHotsell();

    StatusOneVo<GoodsCommodityDetailsVo> details(Long id);

    StatusVo<GoodsCommodityVo> search(Integer page,Integer pageSize,String keyword,Integer priceDesc,Integer dealDesc,Integer two,Integer classification,String figure);

    List<GoodsCommodity> findAllById(List<Long> ids);

    List<GoodsCommodity> findAll();

    StatusOneVo change(String series,List<Long> ids);

    StatusVo<CompanyProfileVo> hot();
}
