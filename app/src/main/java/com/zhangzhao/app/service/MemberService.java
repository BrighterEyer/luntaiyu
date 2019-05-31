package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;

public interface MemberService extends CommonService {

    StatusVoidVo openMember(int type, double money);

    StatusVo members();
}
