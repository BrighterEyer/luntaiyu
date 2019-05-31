package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.BankCardService;
import com.zhangzhao.app.vo.BankCardVo;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.BankCardDto;
import com.zhangzhao.common.entity.BankCard;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 银行卡
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BankCardServiceImpl extends BaseService implements BankCardService {
    /**
     * 添加/修改银行卡信息
     *
     * @param bankCardDto
     * @param bindingResult
     * @return
     */
    @Override
    public StatusVoidVo saveOrUp(BankCardDto bankCardDto, BindingResult bindingResult) {
        StatusVoidVo vo = new StatusVoidVo();
        if (bindingResult.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, bindingResult.getFieldError().getDefaultMessage());
        } else {
            Optional<BankCard> optional = bankCardRepository.findByCardNumber(bankCardDto.getCardNumber());
            if (optional.isPresent()) {
                vo.setMsg("该银行卡号已存在");
            } else {
                BankCard bankCard = bankCardMapper.DtoTobean(bankCardDto);
                bankCard.setUserId(getUser().getId());
                bankCardRepository.saveAndFlush(bankCard);
                vo.success();
            }
        }
        return vo;
    }

    @Override
    public StatusVo<BankCardVo> bankcardAll(Long id) {
        StatusVo<BankCardVo> vo = new StatusVo<>();
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            vo.fail(ErrorCode.ACCOUNT_ERROR, "用户不存在！！");
        }
        return vo;
    }

    @Override
    public StatusVo findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.Direction.DESC, "createTime");
        Page<BankCard> all = bankCardRepository.findByUserIdAndStatus(getUser().getId(),BankCard.Status.NORMAL.getStatus(), pageable);
        List<BankCardVo> collect = all.stream().map(bankCardMapper::beanToVo).collect(Collectors.toList());
        StatusVo vo=new StatusVo();
        vo.success(collect);
        return vo;
    }
}
