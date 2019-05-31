package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.UserWalletVo;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 我的钱包
 */
@Api(tags = "Wallet", description = "我的钱包")
@RestController
@RequestMapping("/app/wallet")
public class WalletController extends BaseService {

    @ApiOperation(value = "1 钱包", notes = "用户钱包", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/q/find/wallet/id")
    public StatusOneVo<UserWalletVo> findAll() {
        return walletService.findWallet(getUser().getId());
    }

    @ApiOperation(value = "2 提现", notes = "提现", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("q/update/balance/id")
    public StatusVoidVo updateBalance(@ApiParam(value = "提现金额", required = true) @RequestParam double balance, @ApiParam(value = "支付宝账号", required = true) @RequestParam String cashAccount, @ApiParam(value = "银行卡号", required = true) @RequestParam String cardNumber) {
        return walletService.updateBalance(getUser().getId(), balance, cashAccount, cardNumber);
    }

    @ApiOperation(value = "3 钱包支付订单", notes = "钱包支付订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/wallet/payment/order/s/id")
    public StatusVoidVo payment(@ApiParam(value = "订单id", required = true) @RequestParam long orderId,
                                @ApiParam(value = "金额", required = true) @RequestParam double paymentPrice,
                                @ApiParam(value = "支付密码", required = true) @RequestParam String paymentPassword) {
        return walletService.payment(orderId, paymentPrice,paymentPassword);
    }

}
