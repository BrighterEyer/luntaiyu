package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.BankCardVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.BankCardDto;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.validation.BindingResult;

public interface BankCardService extends CommonService {
    /**
     * 添加/修改银行卡信息
     *
     * @param bankCardDto
     * @param bindingResult
     * @return
     */
    StatusVoidVo saveOrUp(BankCardDto bankCardDto, BindingResult bindingResult);

    /**
     * 我的银行卡
     *
     * @return
     */
    StatusVo<BankCardVo> bankcardAll(Long id);

    StatusVo findAll(Integer page,Integer pageSize);

}
