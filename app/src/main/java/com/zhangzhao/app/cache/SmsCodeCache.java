package com.zhangzhao.app.cache;

import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.util.AliyunSms;
import com.zhangzhao.common.util.SMSCode;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@CacheConfig(cacheNames = "SMS_CODE_CACHE")
public class SmsCodeCache {

    @Resource
    private CacheManager cacheManager;

    @Cacheable(key = "#phone")
    public SMSCode sendSmsCode(String phone, String name){
        return AliyunSms.smsCode(phone, name);
    }

    public SMSCode getSmsCode(String phone){
        Cache cache = cacheManager.getCache("SMS_CODE_CACHE");
        return cache.get(phone,SMSCode.class);
    }

    /**
     * 验证手机验证码
     * @param phone
     * @param code
     * @param smsCode
     * @return
     */
    public StatusVoidVo verificationCode(String phone, String code, SMSCode smsCode){
        StatusVoidVo statusVo = new StatusVoidVo();
        if(phone==null || !phone.matches("1[3|4|5|7|8][0-9]\\d{8}")){
            statusVo.fail(ErrorCode.PHONE_ERROR,"手机号码错误");
            return  statusVo;
        }
        try {
            if(!smsCode.getCode().equals(code)){
                statusVo.fail(ErrorCode.CODE_ERROR,"验证码错误");
                return  statusVo;
            }
            if (SMSCode.checkCode(smsCode.getExpiryTime())) {
                statusVo.success();
                Cache cache = cacheManager.getCache("SMS_CODE_CACHE");
                cache.evict(phone);
            } else {
                statusVo.fail(ErrorCode.CODE_ERROR,"验证码已过期");
            }
            return statusVo;
        }catch (NullPointerException ex){
            statusVo.fail(ErrorCode.SESSION_ERROR,"手机号与验证码不匹配");
            return statusVo;
        }
    }
}
