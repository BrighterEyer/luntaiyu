package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.WalletService;
import com.zhangzhao.app.vo.UserWalletVo;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.entity.Integral;
import com.zhangzhao.common.entity.OrderSupply;
import com.zhangzhao.common.entity.TradingRecord;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 我的钱包
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WalletServiceImpl extends BaseService implements WalletService {
    /**
     * 我的钱包
     *
     * @param id
     * @return
     */
    @Override
    public StatusOneVo<UserWalletVo> findWallet(Long id) {
        StatusOneVo<UserWalletVo> vo = new StatusOneVo<>();
        User user = userRepository.getOne(id);
        UserWalletVo userWalletVo = userMapper.userUserWalletVo(user);
        vo.success(userWalletVo);

        return vo;
    }

    /**
     * 提现
     *
     * @param id
     * @param balance
     * @return
     */
    @Override
    public StatusVoidVo updateBalance(Long id, double balance, String cashAccount, String cardNumber) {
        StatusVoidVo vo = new StatusVoidVo();
        try {
            User user = userRepository.getOne(id);
            if (user != null && user.getWallet().getBalance() >= balance) {
                walletRepository.updateBalance(id, balance);
                TradingRecord tradingRecord = new TradingRecord();
                tradingRecord.setMoney(balance);
                tradingRecord.setCashAccount(cashAccount);
                tradingRecord.setCardNumber(cardNumber);
                tradingRecordRepository.save(tradingRecord);
                vo.success();
            } else {
                vo.fail(ErrorCode.ACCOUNT_ERROR, "错误,提现超出");
            }

        } catch (Exception e) {
            e.printStackTrace();
            vo.fail(ErrorCode.SYSTEM_ERROR, "提现失败");
        }
        return vo;
    }

    @Override
    public StatusVoidVo payment(long orderId, double paymentPrice,String paymentPassword) {
        StatusVoidVo vo = new StatusVoidVo();
        User user = getUser();
        Optional<OrderSupply> optional = orderSupplyRepository.findById(orderId);
        if(paymentPassword != null && user.getPaymentPassword().equals(paymentPassword)){
            if (optional.isPresent() && user != null && user.getWallet().getBalance() >= paymentPrice) {
                walletRepository.updateBalance(user.getId(), paymentPrice);
                TradingRecord tradingRecord = new TradingRecord();
                tradingRecord.setType(TradingRecord.Type.EXPEND.getType());
                tradingRecord.setMoney(paymentPrice);
                tradingRecord.setUserId(user.getId());
                tradingRecord.setStatus(TradingRecord.Status.SUCCESSD.getStatus());
                tradingRecordRepository.save(tradingRecord);
                Integral integral = new Integral();
                integral.setIntegration(optional.get().getIntegral());
                integral.setUserId(user.getId());
                integral.setType(Integral.Type.GOODS.getType());
                integralRepository.save(integral);
                orderSupplyRepository.updateStatus(orderId,OrderSupply.Status.PENDING_DELIVERY.getStatus());
                vo.success();
            } else {
                vo.fail(ErrorCode.ACCOUNT_ERROR, "余额不足");
            }
        }else{
            vo.fail(ErrorCode.PASSWD_ERROR,"输入支付密码有误,请重新支付");
        }

        return vo;
    }
}
