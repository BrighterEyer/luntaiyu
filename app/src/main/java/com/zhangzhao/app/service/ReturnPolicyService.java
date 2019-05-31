package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;

import java.util.List;

public interface ReturnPolicyService extends CommonService {


    StatusOneVo refund(Long id);

    StatusVoidVo refundPer(Long id, String why, String instructions);

    StatusVo afterSale(List<Long> ids);

    StatusVoidVo salePer(List<Long> ids,Long id, String why, String instructions,String img,int type);

    StatusVo saleList(Integer page, Integer pageSize);

    StatusOneVo saleDetail(Long id);

    StatusVoidVo reissuePer(Long id,int status);

    StatusVoidVo reissueNumber(Long id,String number,String reissueNumber);
}
