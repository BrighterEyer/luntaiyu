package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.UserDto;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.vo.UserStatisticsVo;
import org.springframework.validation.BindingResult;

public interface UserService extends CommonService {

    StatusPageVo<User> masterList(Integer page, Integer limit);

    StatusVoidVo delMaster(Long id);

    UserStatisticsVo statistics();

    StatusOneVo<User> masterUpdate(UserDto userDto, BindingResult results);

    StatusVoidVo updateAudit(Long id, int applyStatus);

    StatusOneVo<User> masterSave(UserDto userDto, BindingResult result);
}
