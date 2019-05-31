package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.UserWalletVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;

public interface WalletService extends CommonService {
    /**
     * 我的钱包
     *
     * @param id
     * @return
     */
    StatusOneVo<UserWalletVo> findWallet(Long id);

    /**
     * 提现
     *
     * @param id
     * @param balance
     * @return
     */
    StatusVoidVo updateBalance(Long id, double balance, String cashAccount, String cardNumber);

    StatusVoidVo payment(long orderId, double paymentPrice,String paymentPassword);
}
