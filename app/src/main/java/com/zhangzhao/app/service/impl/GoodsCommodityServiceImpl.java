package com.zhangzhao.app.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.zhangzhao.app.vo.*;
import com.zhangzhao.common.entity.*;
import com.zhangzhao.common.entity.Properties;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.GoodsCommodityService;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  商品信息
 *
 * @author Administrator
 *
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class GoodsCommodityServiceImpl extends BaseService implements GoodsCommodityService {

    @Override
    public StatusVo<GoodsCommodityVo> findNoyesHotsell() {
        Pageable pageable = PageRequest.of(0, 6, Sort.Direction.DESC, "createTime");
        Page<GoodsCommodity> page = goodsCommodityRepository.findByNoyesHotsell("1", pageable);
        List<GoodsCommodityVo> collect = page.getContent().parallelStream().map(goodsCommodityMapper::beanToVo).collect(Collectors.toList());
        StatusVo<GoodsCommodityVo> vo=new StatusVo<>();
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusOneVo<GoodsCommodityDetailsVo> details(Long id) {
        StatusOneVo<GoodsCommodityDetailsVo> vo=new StatusOneVo<>();
        Optional<GoodsCommodity> optional = goodsCommodityRepository.findById(id);
        if (optional.isPresent()){
            GoodsCommodityDetailsVo goodsCommodityDetailsVo = goodsCommodityMapper.beanToDetailsVo(optional.get());
            List<GoodsCommodity> series = goodsCommodityRepository.findBySeries(optional.get().getSeries());
            List<List<ProperstiesVo>> collect = series.parallelStream().filter(o -> !o.getId().equals(optional.get().getId())).map
                    (GoodsCommodity::getProperties).map(goodsCommodityMapper::beanToGoodsPropersieVo).collect(Collectors.toList());
            List<Map<String,Object>> properties = Lists.newArrayList();
            List<ProperstiesVo> list = collect.stream().flatMap(o -> Arrays.stream(o.toArray(new ProperstiesVo[]{}))).distinct().collect
                    (Collectors.toList());
            List<String> vals = Lists.newArrayList();
            for (ProperstiesVo properstiesVoList: list){
                if (!vals.contains(properstiesVoList.getName())){
                    vals.add(properstiesVoList.getName());
                    Map<String,Object> p1 = Maps.newHashMap();
                    List<Map<String,String>> p2 = Lists.newArrayList();
                    for (ProperstiesVo properstiesVo: list){
                        if (properstiesVoList.getName().equals(properstiesVo.getName())){
                            Map<String,String> p3 = Maps.newHashMap();
                            p3.put("id",properstiesVo.getId());
                            p3.put("name",properstiesVo.getVal());
                            p2.add(p3);
                        }
                    }
                    p1.put("title",properstiesVoList.getName());
                    p1.put("list",p2);
                    properties.add(p1);
                }
            }
            goodsCommodityDetailsVo.setPropersieVoList(new Gson().toJson(properties));
            //属性
            goodsCommodityDetailsVo.setProperty(optional.get().getProperty());
            //安装类别
            goodsCommodityDetailsVo.setInstallationType(optional.get().getInstallationType());
            Activity activity = activityRepository.findByGoodIdAndStartTimeLessThanEqualAndEndTimeAfter(optional.get().getId(), new Date(), new Date());
            if (activity!=null){
                ActivityVo activityVo = activityMapper.beanToVo(activity);
                goodsCommodityDetailsVo.setActivityVo(activityVo);
                goodsCommodityDetailsVo.setPromotionPrice(String.valueOf(activity.getPrice()));
            }
            try {
                if (getUser()!=null){
                    int i = collectRepository.countByUserIdAndGoodsCommodity_Id(getUser().getId(), id);
                    goodsCommodityDetailsVo.setCollect(String.valueOf(i));
                }
            }catch (Exception e){
                e.printStackTrace();
                log.error("用户没登入，没有收藏");
            }
            vo.success(goodsCommodityDetailsVo);
        }
        return vo;
    }

    @Override
    public StatusVo<GoodsCommodityVo> search(Integer page,Integer pageSize,String keyword, Integer priceDesc, Integer dealDesc,Integer two,Integer classification,String figure) {
        StatusVo<GoodsCommodityVo> vo=new StatusVo<>();
        Sort sort = new Sort(Sort.Direction.DESC, "price");
        if (priceDesc==1){
            sort = new Sort(Sort.Direction.ASC, "price");
        }
        if (dealDesc==0){
            sort.and(new Sort(Sort.Direction.DESC, "sales"));
        }else {
            sort.and(new Sort(Sort.Direction.ASC, "sales"));
        }
        Pageable pageable = PageRequest.of(page-1, pageSize, sort);
        Page<GoodsCommodity> all = goodsCommodityRepository.findAll(new Specification<GoodsCommodity>() {
            @Override
            public Predicate toPredicate(Root<GoodsCommodity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(keyword)) {
                    Predicate name = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
                    Predicate figure = criteriaBuilder.like(root.get("figure"), "%" + keyword + "%");
                    Predicate model = criteriaBuilder.like(root.get("model"), "%" + keyword + "%");
                    list.add(criteriaBuilder.or(name, figure, model));
                }else if (two != null){
                    list.add(criteriaBuilder.equal(root.get("twoClassId"),two));
                }else if (classification!=null){
                    list.add(criteriaBuilder.equal(root.get("threeClassId"),classification));
                }
                if (StringUtils.isNotBlank(figure)) {
                    list.add(criteriaBuilder.equal(root.get("figure"),figure));
                }
                //将关键字进行保存
                CompanyProfile company = new CompanyProfile();
                company.setKeyword(keyword);
                companyProfileRepository.save(company);
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        }, pageable);
        List<GoodsCommodityVo> collect = all.getContent().parallelStream().map(goodsCommodityMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }

    @Override
    public List<GoodsCommodity> findAllById(List<Long> ids) {
        return goodsCommodityRepository.findAllById(ids);
    }

    @Override
    public List<GoodsCommodity> findAll() {
        return goodsCommodityRepository.findAll();
    }

    @Override
    public StatusOneVo change(String series, List<Long> ids) {
        StatusOneVo vo=new StatusOneVo();
        List<Properties> list = propertiesRepository.findAllById(ids);
        if (list!=null && list.size()>0){
            Optional<GoodsCommodity> optional = goodsCommodityRepository.findBySeriesAndProperties(series, list);
            if (optional.isPresent()){
                GoodsCommodityVo goodsCommodityVo = goodsCommodityMapper.beanToVo(optional.get());
                vo.success(goodsCommodityVo);
            }else {
                vo.setMsg("商品不存在");
            }
        }
        return vo;
    }

    @Override
    public StatusVo<CompanyProfileVo> hot() {
        StatusVo vo=new StatusVo();
        Pageable pageable = PageRequest.of(0, 8, Sort.Direction.DESC, "cout");
        Page<CompanyProfile> all = companyProfileRepository.findAll(pageable);
        List<CompanyProfileVo> collect = all.stream().map(companyProfileMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }
}
