package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.AdminDto;
import com.zhangzhao.common.entity.admin.Admin;
import com.zhangzhao.common.entity.admin.Authority;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.AdminService;
import com.zhangzhao.web.vo.AdminVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 后台管理
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends BaseService implements AdminService {

    @Override
    public void updatePassword(Long id, String newPassword) {
        adminRepository.updatePassword(id, newPassword);
    }

    @Override
    public StatusPageVo<AdminVo> childrens(Integer page, Integer limit) {
        StatusPageVo<AdminVo> vo = new StatusPageVo<>();
        PageRequest request = PageRequest.of(page - 1, limit, Sort.Direction.DESC, "createTime");
        Page<Admin> findAll = adminRepository.findAll(new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.gt(root.get("parentId"), 0));
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        }, request);
        List<AdminVo> collect = findAll.getContent().parallelStream().map(adminMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect, findAll.getTotalElements());
        return vo;
    }

    @Override
    public StatusVoidVo authSave(Long id,String name,String password, List<Long> auths) {
        StatusVoidVo vo = new StatusVoidVo();
        Optional<Admin> optional = adminRepository.findById(id);
        if (optional.isPresent()) {
            Admin admin = optional.get();
            List<Authority> authorities = null;
            if (auths != null) {
                authorities = authorityRepository.findAllById(auths);
            }
            if (StringUtils.isNotBlank(password)){
                admin.setPassword(new BCryptPasswordEncoder().encode(password));
            }
            admin.setName(name);
            admin.setAuthority(authorities);
            adminRepository.saveAndFlush(admin);
            vo.success();
        } else {
            vo.fail(ErrorCode.USER_ERROR, "用户不存在");
        }
        return vo;
    }

    @Override
    public StatusVoidVo delAdmin(Long id) {
        StatusVoidVo vo = new StatusVoidVo();
        adminRepository.delAdmin_authority(id);
        adminRepository.delAdmin(id);
        vo.success();
        return vo;
    }

    @Override
    public StatusOneVo<Admin> adminSave(AdminDto adminDto, BindingResult results) {
        StatusOneVo<Admin> vo = new StatusOneVo();
        if (results.hasErrors()) {
            vo.setMsg("参数错误");
        } else {
            Optional<Admin> optional = adminRepository.findByName(adminDto.getName());
            if (optional.isPresent()) {
                vo.setMsg("用户名已存在");
            } else {
                Admin newA = new Admin();
                newA.setName(adminDto.getName());
                newA.setPassword(new BCryptPasswordEncoder().encode(adminDto.getPassword()));
                newA.setParentId(getAdmin().getId());
                newA.setIcon(uploadConfig.getDefaultIcon());
                Admin admin = adminRepository.saveAndFlush(newA);
                vo.success(admin);
            }
        }
        return vo;
    }
}
