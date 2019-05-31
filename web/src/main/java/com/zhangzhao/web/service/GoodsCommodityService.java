package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.GoodsCommodityDto;
import com.zhangzhao.common.dto.GoodsCommodityUpdateDto;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.Properties;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface GoodsCommodityService extends CommonService {

    Optional<GoodsCommodity> findById(Long id);

    StatusVoidVo save(GoodsCommodityDto goodsCommodityDto, BindingResult results);

    StatusPageVo findAll(Integer page, Integer pageSize, String name, Integer status);

    StatusVoidVo delById(Long id, Integer status);

    StatusVoidVo save(GoodsCommodityUpdateDto goodsCommodityUpdateDto, BindingResult results);

    HSSFWorkbook export(String startTime, String endTime, HttpServletResponse response);

    StatusPageVo findAll(Integer page, Integer pageSize, Integer type);

    StatusVoidVo save(Properties properties);

    StatusVoidVo delProperties(long id);
}
