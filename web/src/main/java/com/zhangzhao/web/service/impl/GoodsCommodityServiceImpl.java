package com.zhangzhao.web.service.impl;

import com.google.common.collect.Lists;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.GoodsCommodityDto;
import com.zhangzhao.common.dto.GoodsCommodityUpdateDto;
import com.zhangzhao.common.dto.GoodsTablesDto;
import com.zhangzhao.common.entity.GoodSecurity;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.Properties;
import com.zhangzhao.common.util.UtilDate;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.GoodsCommodityService;
import com.zhangzhao.web.vo.GoodsCommodityVo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 商品信息
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsCommodityServiceImpl extends BaseService implements GoodsCommodityService {

    @Override
    public StatusPageVo findAll(Integer page, Integer pageSize, String name, Integer status) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "createTime");
        Page<GoodsCommodity> all = goodsCommodityRepository.findAll(new Specification<GoodsCommodity>() {
            @Override
            public Predicate toPredicate(Root<GoodsCommodity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(name)) {
                    Predicate names = criteriaBuilder.like(root.get("name"), "%" + name + "%");
                    Predicate viceName = criteriaBuilder.like(root.get("viceName"), "%" + name + "%");
                    list.add(criteriaBuilder.or(names, viceName));
                }
                list.add(criteriaBuilder.equal(root.get("status"), status));
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        }, pageable);
        List<GoodsCommodityVo> collect = all.stream().map(goodsCommodityMapper::beanToVo).collect(Collectors.toList());
        StatusPageVo vo = new StatusPageVo();
        vo.success(collect, all.getTotalElements());
        return vo;
    }
    
    @Override
    public Optional<GoodsCommodity> findById(Long id) {
        return goodsCommodityRepository.findById(id);
    }

    @Override
    public StatusVoidVo save(GoodsCommodityDto goodsCommodityDto, BindingResult results) {
        StatusVoidVo vo = new StatusVoidVo();
        if (results.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, results.getFieldError().getDefaultMessage());
        } else {
            List<GoodsCommodity> list = Lists.newArrayList();
            List<GoodSecurity> securities = goodSecurityRepository.findAllById(goodsCommodityDto.getGoodSecuritie());
            String num = UtilDate.getOrderNum();
            Date date = new Date();
            for (GoodsTablesDto o : goodsCommodityDto.getTables()) {
                GoodsCommodity goodsCommodity = goodsCommodityMapper.dtoToBean(goodsCommodityDto);
                List<Properties> propertiesList = propertiesRepository.findAllById(o.getPropert());
                BeanUtils.copyProperties(o, goodsCommodity);
                goodsCommodity.setProperties(propertiesList);
                goodsCommodity.setProperty(StringUtils.join(propertiesList.stream().map(Properties::getVal).collect(Collectors.toList()), ","));
                goodsCommodity.setSeries(num);
                goodsCommodity.setGoodSecurities(securities);
                goodsCommodity.setCreateTime(date);
                list.add(goodsCommodity);
            }
            goodsCommodityRepository.saveAll(list);
            vo.success();

        }
        return vo;
    }

    @Override
    public StatusVoidVo delById(Long id, Integer status) {
        StatusVoidVo vo = new StatusVoidVo();
        Optional<GoodsCommodity> optional = goodsCommodityRepository.findById(id);
        if (optional.isPresent()) {
            GoodsCommodity goodsCommodity = optional.get();
            goodsCommodity.setStatus(status);
            goodsCommodityRepository.save(goodsCommodity);
            vo.success();
        }
        return vo;
    }

    @Override
    public StatusVoidVo save(GoodsCommodityUpdateDto goodsCommodityUpdateDto, BindingResult results) {
        StatusVoidVo vo = new StatusVoidVo();
        if (results.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, results.getFieldError().getDefaultMessage());
        } else {
            Optional<GoodsCommodity> optional = goodsCommodityRepository.findById(goodsCommodityUpdateDto.getId());
            if (optional.isPresent()) {
                List<GoodSecurity> securities = goodSecurityRepository.findAllById(goodsCommodityUpdateDto.getGoodSecuritie());
                GoodsCommodity goodsCommodity = optional.get();
                BeanUtils.copyProperties(goodsCommodityUpdateDto, goodsCommodity);
                goodsCommodity.setGoodSecurities(securities);
                goodsCommodityRepository.save(goodsCommodity);
                vo.success();
            }
        }
        return vo;
    }

    @Override
    public HSSFWorkbook export(String startTime, String endTime, HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            try {
                Date start = format.parse(startTime);
                Date end = format.parse(endTime);
                List<GoodsCommodity> goodsCommodities = goodsCommodityRepository.findAll(new Specification<GoodsCommodity>() {
                    @Override
                    public Predicate toPredicate(Root<GoodsCommodity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                        List<Predicate> list = new ArrayList<>();
                        list.add(criteriaBuilder.between(root.get("createTime"), start, end));
                        return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
                    }
                });
                if (goodsCommodities != null && goodsCommodities.size() > 0) {
                    HSSFSheet sheet = wb.createSheet("商品报表");
                    HSSFRow row = sheet.createRow(0);
                    HSSFFont font = wb.createFont();
                    font.setColor(HSSFFont.COLOR_RED);
                    font.setFontName("黑体");
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    HSSFCellStyle style = wb.createCellStyle();
                    style.setFont(font);
                    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                    style.setWrapText(true);

                    HSSFCell cell = row.createCell(0);
                    cell.setCellValue("Id");
                    cell.setCellStyle(style);

                    cell = row.createCell(1);
                    cell.setCellValue("名称");
                    cell.setCellStyle(style);

                    cell = row.createCell(2);
                    cell.setCellValue("促销价");
                    cell.setCellStyle(style);

                    cell = row.createCell(3);
                    cell.setCellValue("库存");
                    cell.setCellStyle(style);

                    cell = row.createCell(4);
                    cell.setCellValue("销量");
                    cell.setCellStyle(style);

                    cell = row.createCell(5);
                    cell.setCellValue("状态");
                    cell.setCellStyle(style);

                    cell = row.createCell(6);
                    cell.setCellValue("创建时间");
                    cell.setCellStyle(style);

                    sheet.autoSizeColumn((short) 6);
                    sheet.setColumnWidth(6, 10 * 256);

                    for (int i = 0; i < goodsCommodities.size(); i++) {
                        row = sheet.createRow(i + 1);
                        row.createCell(0).setCellValue(goodsCommodities.get(i).getId());
                        row.createCell(1).setCellValue(goodsCommodities.get(i).getName());
                        row.createCell(2).setCellValue(goodsCommodities.get(i).getPromotionPrice());
                        row.createCell(3).setCellValue(goodsCommodities.get(i).getInventory());
                        row.createCell(4).setCellValue(goodsCommodities.get(i).getSales());
                        int o = goodsCommodities.get(i).getStatus();
                        if (o == 0) {
                            row.createCell(5).setCellValue("正常");
                        } else if (o == 1) {
                            row.createCell(5).setCellValue("删除");
                        } else if (o == 2) {
                            row.createCell(5).setCellValue("信息费支出");
                        }
                        if (goodsCommodities.get(i).getCreateTime() != null) {
                            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            row.createCell(6).setCellValue(format.format(goodsCommodities.get(i).getCreateTime()));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    @Override
    public StatusPageVo findAll(Integer page, Integer pageSize, Integer type) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Properties> all = propertiesRepository.findAll(new Specification<Properties>() {
            @Override
            public Predicate toPredicate(Root<Properties> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (type != null) {
                    list.add(criteriaBuilder.equal(root.get("parentId"), type));
                }
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        }, pageable);
        StatusPageVo vo = new StatusPageVo();
        vo.success(all.getContent(), all.getTotalElements());
        return vo;
    }

    @Override
    public StatusVoidVo save(Properties properties) {
        StatusVoidVo vo = new StatusVoidVo();
        if (properties.getParentId() != null && properties.getParentId() > 0) {
            Properties one = propertiesRepository.getOne(properties.getParentId());
            properties.setName(one.getName());
        }
        propertiesRepository.save(properties);
        vo.success();
        return vo;
    }

    @Override
    public StatusVoidVo delProperties(long id) {
        StatusVoidVo vo = new StatusVoidVo();
        propertiesRepository.deleteByIdOrParentId(id, id);
        vo.success();
        return vo;
    }
}
