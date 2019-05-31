package com.zhangzhao.app.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.common.collect.Maps;
import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.config.AlipayConfig;
import com.zhangzhao.app.config.WechatProperties;
import com.zhangzhao.app.service.impl.OrderSupplyServiceImpl;
import com.zhangzhao.app.util.StringUtil;
import com.zhangzhao.app.vo.*;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.OrderSupplyDto;
import com.zhangzhao.common.entity.*;
import com.zhangzhao.common.util.NumberUtil;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "OrderSupply", description = "订单")
@RestController
@RequestMapping("/app/ordersupply")
@CrossOrigin
public class OrderSupplyController extends BaseService {

    @Autowired
    private WxPayService wxService;

    @PostMapping("/order/supply/s/place/order")
    @ApiOperation(value = "1 下单前", notes = "下单前", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<PlaceOrderVo> placeOrder(@ApiParam(value = "商品id") @RequestParam(required = false) Long id,
                                                @ApiParam(value = "类型") @RequestParam(required = false) Integer type,
                                                @ApiParam(value = "安装类型") @RequestParam(required = false) String installationType,
                                                @ApiParam(value = "购买数量") @RequestParam(required = false) Integer amount,
                                                @ApiParam(value = "购物车ids") @RequestParam(required = false) List<Long> ids) {
        return orderSupplyService.placeOrder(id, installationType, amount, ids);
    }

    @PostMapping("/order/supply/s/save/order")
    @ApiOperation(value = "2 下单", notes = "下单", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo saveOrder(@ApiParam(value = "请求参数封装") @RequestBody @Valid OrderSupplyDto orderSupplyDto,
                                 BindingResult bindingResult) {
        return orderSupplyService.saveOrder(orderSupplyDto, bindingResult);
    }

    @PostMapping("/order/supply/s/unifiedOrder")
    @ApiOperation(value = "3 微信统一下单", notes = "微信统一下单", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<WeiXinOrderVo> unifiedOrder(HttpServletRequest request, @ApiParam(value = "订单id", required = true) @RequestParam Long id) {
        StatusOneVo<WeiXinOrderVo> vo = new StatusOneVo<WeiXinOrderVo>();
        try {
            OrderSupply orderSupply = orderSupplyService.findById(id);
            WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
            wxPayUnifiedOrderRequest.setDeviceInfo(WechatProperties.DEVICE_INFO);
            wxPayUnifiedOrderRequest.setNonceStr(StringUtil.getRandomStr(32));
            wxPayUnifiedOrderRequest.setBody(orderSupply.getOrderDetails().get(0).getName() + "商品消费");
            wxPayUnifiedOrderRequest.setOutTradeNo(orderSupply.getOrderNumber());
            wxPayUnifiedOrderRequest.setTotalFee((int) (orderSupply.getPaymentPrcie() * 100));
            wxPayUnifiedOrderRequest.setSpbillCreateIp(StringUtil.getIpAddr(request));
            wxPayUnifiedOrderRequest.setNotifyUrl(wxService.getConfig().getNotifyUrl());
            wxPayUnifiedOrderRequest.setTradeType(WxPayConstants.TradeType.APP);
            WxPayUnifiedOrderResult unifiedOrder = this.wxService.unifiedOrder(wxPayUnifiedOrderRequest);
            if (unifiedOrder.getResultCode().equals(WxPayConstants.RefundStatus.SUCCESS) && unifiedOrder.getReturnCode().equals(WxPayConstants.RefundStatus.SUCCESS)) {
                String appid = unifiedOrder.getAppid();
                String prepayId = unifiedOrder.getPrepayId();
                String timeStamp = StringUtil.getTimeStamp();
                String nonceStr = StringUtil.getRandomStr(20);
                SortedMap<String, Object> signMap = Maps.newTreeMap();
                signMap.put("appid", appid);
                signMap.put("partnerid", unifiedOrder.getMchId());
                signMap.put("prepayid", prepayId);
                signMap.put("package", "Sign=WXPay");
                signMap.put("noncestr", nonceStr);
                signMap.put("timestamp", timeStamp);
                signMap.put("sign", wxService.getConfig().getMchKey());
                String sign = StringUtil.getSign(signMap);
                signMap.put("sign", sign);
                WeiXinOrderVo weiXinOrderVo = (WeiXinOrderVo) StringUtil.mapToObject(signMap, WeiXinOrderVo.class);
                weiXinOrderVo.setPackages("Sign=WXPay");
                vo.success(weiXinOrderVo);
            } else {
                vo.fail(ErrorCode.WECHAT_PAY_ERROR, unifiedOrder.getReturnMsg() + "--微信统一下单失败，订单号------" + id);
            }
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
            vo.fail(ErrorCode.WECHAT_PAY_ERROR, "微信统一下单失败，订单号------" + id);
        }
        return vo;
    }

    @ApiOperation(value = "4 微信支付回调", notes = "微信支付回调", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/s/notify/weixin/pay", produces = MediaType.APPLICATION_JSON_VALUE)
    public String notifyWeiXinPay(HttpServletRequest request)
            throws Exception {
        String xmlStr = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        WxPayOrderNotifyResult notifyResult = wxService.parseOrderNotifyResult(xmlStr);
        Map<String, Object> return_data = new HashMap<String, Object>(2);
        if (!notifyResult.getResultCode().equals(WxPayConstants.RefundStatus.SUCCESS)) {
            return_data.put("return_code", "FAIL");
            return_data.put("return_msg", "return_code不正确");
            log.error("return_code不正确");
        } else {
            try {
                Integer totalFee = notifyResult.getTotalFee();
                String outTradeNo = notifyResult.getOutTradeNo();
                double total = new BigDecimal(totalFee).divide(new BigDecimal(100)).doubleValue();
                OrderSupply orderSupply = orderSupplyService.findOrderByNumber(outTradeNo);
                if (orderSupply.getPaymentPrcie() == total) {
                    return_data.put("return_code", "SUCCESS");
                    return_data.put("return_msg", "OK");
                    if (orderSupply.getStatus() > 1) {
                        return StringUtil.mapToxml(return_data);
                    } else if (orderSupply.getStatus() == 0) {
                        notifys(orderSupply);
                    }
                }
            } catch (Exception e) {
                log.error("回调错误:原因:" + notifyResult.getReturnMsg());
                e.printStackTrace();
            }
        }
        return StringUtil.mapToxml(return_data);
    }

    @ApiOperation(value = "5 支付宝支付", notes = "支付宝支付", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/order/supply/s/placeAn/order")
    public String placeAnOrder(@ApiParam(value = "订单编号", required = true) @RequestParam(required = false) Long id) {
        String orderString = "";
        OrderSupply orderSupply = orderSupplyService.findById(id);
        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                    AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                    AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();

            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

            //业务参数传入,可以传很多，参考API
            //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
            model.setBody(orderSupply.getOrderDetails().parallelStream().map(o -> o.getName()).collect(Collectors.toList()).toString());
            //商品名称
            model.setSubject(orderSupply.getOrderDetails().parallelStream().map(o -> o.getName()).collect(Collectors.toList()).toString());
            //商户订单号(自动生成)
            model.setOutTradeNo(orderSupply.getOrderNumber());
            //交易超时时间
            // model.setTimeoutExpress("30m");
            //支付金额
            model.setTotalAmount(String.valueOf(orderSupply.getPaymentPrcie()));
            //销售产品码（固定值）
            model.setProductCode("QUICK_MSECURITY_PAY");
            ali_request.setBizModel(model);
            //异步回调地址（后台）
            ali_request.setNotifyUrl(AlipayConfig.notify_url);
            //同步回调地址（APP）
            ali_request.setReturnUrl(AlipayConfig.return_url);

            // 这里和普通的接口调用不同，使用的是sdkExecute
            //返回支付宝订单信息(预处理)
            AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(ali_request);
            //就是orderString 可以直接给APP请求，无需再做处理。
            orderString = alipayTradeAppPayResponse.getBody();
            // this.createAlipayMentOrder(orderSupply);//创建新的商户支付宝订单

        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error("与支付宝交互出错，未能生成订单，请检查代码！" + e.toString());
        }
        return orderString;
    }

    @ApiOperation(value = "6 支付宝回调", notes = "支付宝回调", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/s/notify/ali/pay", produces = MediaType.APPLICATION_JSON_VALUE)
    public String notifAliPay(HttpServletRequest request) {
        Map<String, String[]> aliParams = request.getParameterMap();
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            conversionParams.put(key, valueStr);
        }

        boolean signVerified = false;
        try {
            //调用SDK验证签名
            signVerified = AlipaySignature.rsaCheckV1(conversionParams, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            log.error("==================验签失败" + e.getErrMsg());
            e.printStackTrace();
        }

        //对验签进行处理
        if (signVerified) {
            //验签通过
            String appId = conversionParams.get("app_id");//支付宝分配给开发者的应用Id
            String notifyTime = conversionParams.get("notify_time");//通知时间:yyyy-MM-dd HH:mm:ss
            String gmtCreate = conversionParams.get("gmt_create");//交易创建时间:yyyy-MM-dd HH:mm:ss
            String gmtPayment = conversionParams.get("gmt_payment");//交易付款时间
            String gmtRefund = conversionParams.get("gmt_refund");//交易退款时间
            String gmtClose = conversionParams.get("gmt_close");//交易结束时间
            String tradeNo = conversionParams.get("trade_no");//支付宝的交易号
            String outTradeNo = conversionParams.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
            String outBizNo = conversionParams.get("out_biz_no");//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
            String buyerLogonId = conversionParams.get("buyer_logon_id");//买家支付宝账号
            String sellerId = conversionParams.get("seller_id");//卖家支付宝用户号
            String sellerEmail = conversionParams.get("seller_email");//卖家支付宝账号
            String totalAmount = conversionParams.get("total_amount");//订单金额:本次交易支付的订单金额，单位为人民币（元）
            String receiptAmount = conversionParams.get("receipt_amount");//实收金额:商家在交易中实际收到的款项，单位为元
            String invoiceAmount = conversionParams.get("invoice_amount");//开票金额:用户在交易中支付的可开发票的金额
            String buyerPayAmount = conversionParams.get("buyer_pay_amount");//付款金额:用户在交易中支付的金额
            String tradeStatus = conversionParams.get("trade_status");// 获取交易状态

            //支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）
            OrderSupply orderSupply = orderSupplyRepository.findByOrderNumber(outTradeNo);

            if (orderSupply != null && totalAmount.equals(String.valueOf(orderSupply.getPaymentPrcie())) && AlipayConfig.APPID.equals(appId)) {
                if (orderSupply.getStatus() > 1) {
                    return "success";
                } else if (orderSupply.getStatus() == 0) {
                    try {
                        if (tradeStatus.equals("TRADE_SUCCESS")) {
                            notifys(orderSupply);
                            return "success";
                        } else {
                            return "fail";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                log.error("==================支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）,不一致！返回fail");
                return "fail";
            }

        } else {
            log.error("==================验签不通过 ！");
            return "fail";
        }
        return "fail";
    }

    @ApiOperation(value = "7 订单列表", notes = "订单列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/order/supply/s/order/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<OrderSupplyVo> orderList(@ApiParam(value = "当前页") @RequestParam(required = false, defaultValue = "1") Integer page,
                                             @ApiParam(value = "页数") @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                             @ApiParam(value = "订单状态 1-待付款 2-待发货 3-待收货 4-待评价 5-已完成") @RequestParam(required = false) String status) {
        return orderSupplyService.findAll(page, pageSize, status);
    }

    @ApiOperation(value = "8 订单详情", notes = "订单详情", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/order/supply/s/order/detail/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<OrderSupplyDetailVo> orderDetail(@ApiParam(value = "订单id", required = true) @RequestParam Long id) {
        return orderSupplyService.findOne(id);
    }

    @ApiOperation(value = "9 订单状态改变", notes = "订单状态改变", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/order/supply/s/del/cancel/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo delCancel(@ApiParam(value = "订单id", required = true) @RequestParam Long id,
                                  @ApiParam(value = "状态 -2取消 1-待付款 2-待发货 3-待收货 4-待评价 5-已完成 6-退款 7-退货退款 8-已补发 9-待商家确认退款 10-退货退款待确认 13-待商家确认补发 " +
                                          "15-质量审核",
                                          required = true) @RequestParam int type) {
        return orderSupplyService.updateStatus(id, type);
    }


    public void notifys(OrderSupply orderSupply) {
        try {
            User user = orderSupply.getUser();
            if (user != null) {
                int amount = 0;
                double hPrice = 0;
                List<Long> ids = orderSupply.getOrderDetails().parallelStream().map(o -> o.getGoodsCommodityId()).collect(Collectors.toList());
                List<GoodsCommodity> all = goodsCommodityService.findAll();
                List<GoodsCommodity> list = all.parallelStream().filter(o->ids.contains(o.getId())).collect(Collectors.toList());
                List<Activity> activities = activityService.findAllByGoodId(ids);//商品返回
                if (activities.size() > 0) {
                    for (Activity activity : activities) {
                        for (OrderDetails orderDetails : orderSupply.getOrderDetails()) {
                            if (activity.getGoodId().equals(orderDetails.getGoodsCommodityId()) && orderDetails.getPaymentPrcie() >= activity.getPrice()) {
                                Coupon coupon = new Coupon();
                                coupon.setName("优惠券");
                                coupon.setType(Coupon.Type.COUPON.getType());
                                coupon.setPrice(NumberUtil.formatDouble(activity.getReturnPrice()));
                                coupon.setUserId(user.getId());
                                couponService.saveBean(coupon);
                            }
                        }
                    }
                }
                for (OrderDetails orderDetails : orderSupply.getOrderDetails()) {//计算优惠商品总数与总价格
                    for (GoodsCommodity goodsCommodity : list) {
                        if (goodsCommodity.getDiscount() == 0 && orderDetails.getGoodsCommodityId().equals(goodsCommodity.getId())) {
                            amount += orderDetails.getAmount();
                            hPrice += NumberUtil.formatDouble(orderDetails.getPrice() * orderDetails.getAmount());
                        }
                    }
                }
                if (user.getMember() == 1) {//会员优惠模式
                    if (user.getAmount() == 0 && user.getReceipts() == 0) {
                        user.setAmount(user.getAmount() + amount);
                        user.setReceipts(NumberUtil.formatDouble(user.getReceipts() + hPrice));
                    } else {
                        double v = NumberUtil.formatDouble(user.getReceipts() + hPrice);
                        amount += user.getAmount();
                        List<GoodsCommodity> collect = list.parallelStream().filter(o -> o.getDiscount() == 0).collect(Collectors.toList());
                        List<UserGoods> userGoods = user.getUserGoods();
                        for (GoodsCommodity goodsCommodity : collect) {
                            ok:
                            for (OrderDetails orderDetails : orderSupply.getOrderDetails()) {
                                if (goodsCommodity.getId().equals(orderDetails.getGoodsCommodityId())) {
                                    for (UserGoods userGoods1 : userGoods) {
                                        if (userGoods1.getGoodId().equals(goodsCommodity.getId())) {
                                            userGoods1.setAmount(userGoods1.getAmount() + orderDetails.getAmount());
                                            break ok;
                                        }
                                    }
                                }
                            }
                        }
                        user.setUserGoods(userGoods);
                        double price = 0;
                        for (GoodsCommodity goodsCommodity : all) {
                            for (UserGoods userGoods1 : userGoods) {
                                if (userGoods1.getGoodId().equals(goodsCommodity.getId())) {
                                    if (amount <= OrderSupplyServiceImpl.A) {
                                        price += goodsCommodity.getPrice() * userGoods1.getAmount();
                                    } else if (amount > OrderSupplyServiceImpl.A && amount <= OrderSupplyServiceImpl.B) {
                                        price += goodsCommodity.getMemberXprcie() * userGoods1.getAmount();
                                    } else if (amount > OrderSupplyServiceImpl.B) {
                                        price += goodsCommodity.getMemberYprcie() * userGoods1.getAmount();
                                    }
                                }
                            }
                        }
                        double formatDouble = NumberUtil.formatDouble(v - price);
                        if (amount > OrderSupplyServiceImpl.A && formatDouble > 0) {
                            Coupon coupon = new Coupon();
                            coupon.setName("优惠券");
                            coupon.setType(Coupon.Type.COUPON.getType());
                            coupon.setPrice(formatDouble);
                            coupon.setUserId(user.getId());
                            couponService.saveBean(coupon);
                            user.setReceipts(NumberUtil.formatDouble(v - formatDouble));
                        } else {
                            user.setReceipts(NumberUtil.formatDouble(v));
                        }
                        user.setAmount(amount);
                    }
                } else if (user.getMember() == 2) {
                    UserCooperate userCooperate = user.getUserCooperate();
                    if (userCooperate.getComplete() == 0) {
                        for (OrderDetails orderDetails : orderSupply.getOrderDetails()) {
                            if (orderDetails.getGoodsCommodityId().equals(userCooperate.getCommodityId())) {
                                userCooperate.setNums(userCooperate.getNums() + orderDetails.getAmount());
                            }
                        }
                        if (userCooperate.getQuantity() <= userCooperate.getNums()) {
                            Coupon coupon = new Coupon();
                            coupon.setName("优惠券");
                            coupon.setType(Coupon.Type.COUPON.getType());
                            coupon.setPrice(NumberUtil.formatDouble(userCooperate.getQuantity() * userCooperate.getPreferentialPriceZ()));
                            coupon.setUserId(user.getId());
                            couponService.saveBean(coupon);
                            userCooperate.setStatus(UserCooperate.Status.yescoupon.getStatus());
                            userCooperate.setComplete(UserCooperate.Status.yescoupon.getStatus());
                        }
                        user.setUserCooperate(userCooperate);
                    }
                }
                user.setIntegral(user.getIntegral() + orderSupply.getIntegral());
                userService.saveBean(user);
                Integral integral = new Integral();
                integral.setIntegration(orderSupply.getIntegral());
                integral.setUserId(user.getId());
                integral.setType(Integral.Type.GOODS.getType());
                integralService.saveBean(integral);
                TradingRecord record = new TradingRecord();
                record.setType(TradingRecord.Type.EXPEND.getType());
                record.setMoney(orderSupply.getPaymentPrcie());
                record.setUserId(user.getId());
                tradingRecordService.saveBean(record);
            }
            orderSupplyService.updateStatus(orderSupply.getId(), OrderSupply.Status.PENDING_DELIVERY.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{}","订单支付回调处理失败");
        }
    }

    @ApiOperation(value = "10 快捷下单列表", notes = "快捷下单列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/order/supply/q/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<OrderSupplyVo> findOrder() {
        return orderSupplyService.findOrder(getUser().getId());
    }

    @PostMapping("/order/supply/q/place/order")
    @ApiOperation(value = "11 再来一单", notes = "再来一单", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<OrderDetailsVo> againOrder(@ApiParam(value = "订单id") @RequestParam(required = false) Long orderId) {
        return orderSupplyService.againOrder(orderId);
    }

}
