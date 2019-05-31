package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.AdminDto;
import com.zhangzhao.common.entity.admin.Admin;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.vo.AdminVo;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface AdminService extends CommonService {

    void updatePassword(Long id, String newPassword);

    StatusPageVo<AdminVo> childrens(Integer page, Integer limit);

    StatusVoidVo authSave(Long id,String name,String password, List<Long> auths);

    StatusVoidVo delAdmin(Long id);

    StatusOneVo<Admin> adminSave(AdminDto adminDto, BindingResult results);
}
