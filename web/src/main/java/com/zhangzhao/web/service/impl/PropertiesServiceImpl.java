package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.entity.Properties;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.PropertiesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品属性
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PropertiesServiceImpl extends BaseService implements PropertiesService {

    @Override
    public List<Properties> findAll() {
        List<Properties> all = propertiesRepository.findAll();
        List<Properties> collect = all.stream().filter(o -> o.getParentId() > 0).collect(Collectors.toList());
        all=all.stream().filter(o->o.getParentId()==0).collect(Collectors.toList());
        for (Properties properties:all){
            for (Properties c:collect){
                if (properties.getId().equals(c.getParentId())){
                    properties.getProperties().add(c);
                }
            }
        }
        return all;
    }
}
