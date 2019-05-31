package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.vo.MemberVo;
import com.zhangzhao.common.entity.Member;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.entity.Wallet;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.MemberService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl extends BaseService implements MemberService {

    @Override
    public StatusVoidVo openMember(int type, double money) {
        StatusVoidVo vo = new StatusVoidVo();
        if (type == 1 || type == 2 || type == 3 || type == 4) {
            User user = getUser();
            if (user.getWallet().getBalance()>=money){
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                if (type == Member.TimeLimit.ONE.getStatus()) {
                    c.add(Calendar.MONTH, +1);
                } else if (type == Member.TimeLimit.THREE.getStatus()) {
                    c.add(Calendar.MONTH, +3);
                } else if (type == Member.TimeLimit.SIX.getStatus()) {
                    c.add(Calendar.MONTH, +6);
                } else if (type == Member.TimeLimit.TWELVE.getStatus()) {
                    c.add(Calendar.MONTH, +12);
                }
                user.setMembershipTime(c.getTime());
                user.setMember(User.member.member.getType());

                Wallet wallet = user.getWallet();
                wallet.setBalance(wallet.getBalance()-money);
                walletRepository.save(wallet);
                userRepository.saveAndFlush(user);
                vo.success();
            }else {
                vo.setMsg("余额不足");
            }
        }
        return vo;
    }

    @Override
    public StatusVo members() {
        StatusVo vo=new StatusVo();
        List<Member> all = memberRepository.findAll();
        List<MemberVo> collect = all.parallelStream().map(memberMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }
}
