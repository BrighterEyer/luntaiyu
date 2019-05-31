package com.zhangzhao.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wechat")
public class WechatCommon {

    private WechatProperties wechatProperties = new WechatProperties();
}
