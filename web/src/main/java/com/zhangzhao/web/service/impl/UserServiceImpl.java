package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.dto.UserDto;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.UserService;
import com.zhangzhao.web.vo.UserStatisticsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

/**
 * 用户
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService implements UserService {


    @Override
    public StatusPageVo<User> masterList(Integer page, Integer limit) {
        PageRequest request = new PageRequest(page - 1, limit, Sort.Direction.DESC, "createTime");
        Page<User> all = userRepository.findByTypeOrApplyStatus(2, 0, request);
        StatusPageVo<User> vo = new StatusPageVo<>();
        vo.success(all.getContent(), all.getTotalElements());
        return vo;
    }

    @Override
    public StatusVoidVo delMaster(Long id) {
        StatusVoidVo vo = new StatusVoidVo();
        userRepository.delMaster(id);
        vo.success();
        return vo;
    }

    @Override
    public UserStatisticsVo statistics() {
        UserStatisticsVo vo = new UserStatisticsVo();
        try {
            //订单
            vo.setZongChengjiaoe(userRepository.sumBypaymentPrcie());
            vo.setChengJiaocishu(userRepository.countByStatus());
            vo.setYear(userRepository.yearMoney());
            vo.setQuarter(userRepository.quarterMoney());
            vo.setMonth(userRepository.monthMoney());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public StatusOneVo<User> masterUpdate(UserDto userDto, BindingResult results) {
        StatusOneVo<User> vo = new StatusOneVo<>();
        if (results.hasErrors()) {
            vo.setMsg("参数错误");
        } else {
            try {
                User user = new User();
                user.setId(userDto.getId());
                user.setReaName(userDto.getReaName());
                user.setIdCard(userDto.getIdCard());
                user.setPhone(userDto.getPhone());
                user.setProvince(userDto.getProvince());
                user.setDistrict(userDto.getDistrict());
                user.setCity(userDto.getCity());
                user.setDetailedAddress(userDto.getDetailedAddress());
                user.setStatus(userDto.getStatus());
                User use = userRepository.save(user);
                vo.success(use);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return vo;
    }

    @Override
    public StatusVoidVo updateAudit(Long id, int applyStatus) {
        StatusVoidVo vo = new StatusVoidVo();
        userRepository.updateAudit(id, applyStatus);
        vo.success();
        return vo;
    }

    @Override
    public StatusOneVo<User> masterSave(UserDto userDto, BindingResult result) {
        StatusOneVo<User> vo = new StatusOneVo<>();
        if (result.hasErrors()) {
            vo.setMsg("参数错误");
        } else {
            try {
                User user = new User();
                user.setId(userDto.getId());
                user.setReaName(userDto.getReaName());
                user.setIdCard(userDto.getIdCard());
                user.setPhone(userDto.getPhone());
                user.setProvince(userDto.getProvince());
                user.setDistrict(userDto.getDistrict());
                user.setCity(userDto.getCity());
                user.setDetailedAddress(userDto.getDetailedAddress());
                user.setStatus(User.Status.NORMAL.getStatus());
                user.setType(User.Type.MASTER.getType());
                user.setApplyStatus(User.ApplyStatus.YES.getApplyStatus());
                User use = userRepository.save(user);

                vo.success(use);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return vo;
    }
}
