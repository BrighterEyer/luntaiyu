package com.zhangzhao.common.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zhangzhao.common.util.chuanglan.ChuangLanSmsUtil;
import com.zhangzhao.common.util.chuanglan.SmsSendRequest;
import com.zhangzhao.common.util.chuanglan.SmsSendResponse;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 * <p>
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
@Component
public class AliyunSms {

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI7pKUqBB7a0pZ";
    static final String accessKeySecret = "UdMBuAMIX5SHGMwHkcrKgqajXOplIA";

    public static SendSmsResponse sendSms(String phone, String name, String code) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("MacauPropert");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_143705273");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":" + code + "}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        System.out.println("request:--------------------" + request.getPhoneNumbers());
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }

    public static final String charset = "utf-8";
    // 请登录zz.253.com 获取创蓝API账号(非登录账号,示例:N1234567)
    public static final String account = "N512555_N3416556";
    // 请登录zz.253.com 获取创蓝API密码(非登录密码)
    public static final String pswd = "xMVlTepBQjca6a";
    //短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
    public static final String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";

    public static SmsSendResponse sendSms(String phone, String msg) throws UnsupportedEncodingException {
        // 设置您要发送的内容：其中“【】”中括号为运营商签名符号，多签名内容前置添加提交
        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone);
        String requestJson = JSON.toJSONString(smsSingleRequest);
        System.out.println("before request string is: " + requestJson);
        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
        System.out.println("response after request result is :" + response);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        System.out.println("response  toString is :" + smsSingleResponse);
        return smsSingleResponse;
    }

    /**
     * 生成随机数
     *
     * @param num 位数
     * @return
     */
    public static String createRandomNum(int num) {
        String randomNumStr = "";
        for (int i = 0; i < num; i++) {
            int randomNum = (int) (Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }


    /**
     * 发送验证码
     *
     * @param phone
     * @param name
     * @return
     * @throws ClientException
     */
    public static SMSCode smsCode(String phone, String name) {
        SMSCode smsCode = new SMSCode();
        String code = AliyunSms.createRandomNum(6);
        SendSmsResponse sendSmsResponse = null;
        SmsSendResponse smsSendResponse;
        try {
            smsSendResponse = sendSms(phone, "您好，您的验证码为：" + code + "，有效期为5分钟");
        } catch (Exception c) {
            smsCode.setSuccess(false);
            smsCode.setCode(code);
            return smsCode;
        }
        if (smsSendResponse.getCode().equals("0")) {
            smsCode.setSuccess(true);
        } else {
            smsCode.setCode(code);
            smsCode.setSuccess(false);
            return smsCode;
        }
        smsCode.setCode(code);
        //smsCode.setCode("123456");
        smsCode.setExpiryTime();
        smsCode.setPhone(phone);
        return smsCode;
    }

}
