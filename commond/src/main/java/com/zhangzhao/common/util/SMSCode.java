package com.zhangzhao.common.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by zzq on 18/7/6.
 * 短信验证码
 */
public class SMSCode implements Serializable {
    private String phone;//接收短信的手机号码
    private String code;//验证码
    private boolean success;//标记是否发送成功
    private Date expiryTime;//短信的创建时间，格式：年-月-日 时:分:秒（如2013-02-01 15:38:09）

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 30);
        this.expiryTime = calendar.getTime();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static boolean checkCode(Date expiryTime) {
        if (expiryTime.before(new Date())) {//如果过期时间小于当前时间  说明已经过期了
            return false;
        }
        return true;
    }
}
