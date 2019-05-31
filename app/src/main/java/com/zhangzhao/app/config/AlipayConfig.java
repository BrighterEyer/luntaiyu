package com.zhangzhao.app.config;

public class AlipayConfig {

    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088*********4";
    // 1.商户appid
    public static String APPID = "2018091261332320";

    //2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC9q6Vrb3uRPhrv\n" +
            "I55SLqRTzA3vsxWN8gV2yebk9VYbgyW0u0xVhaeXK166Q9wYaw1ldcPwIm4zsPhk\n" +
            "ip68d4PvqGN6OPd966o4ezHG+YFsVxSejUeQlieE3DHpsXKDnKk9a9cNfAWy5Acm\n" +
            "QbDmUYQN8lFnlnMKZkjfpP7XDWiDDLlm26oR0K16ptx903zo3VJJQAay+lwRVpDI\n" +
            "t07VrFcZ24xS3W8RHo92iDMepeEfjq7slgKEpxxhXQ+kprbyqGChyDpRgnuVHV6S\n" +
            "jFPmxkKZPCAU6r13biJZihcFq2szLtZ2YmZYAsGkzxoS2EZC1ULgKcVAT0MO/o6l\n" +
            "HHZrPhqvAgMBAAECggEALakBtKBs7eU2lLVNatlho+6uuDKzkK1f1V37oMjTBuKG\n" +
            "lEC2uqvnUYQWVN77QEGdQvybw9Z2EymaUpHwbsySzYILGKRYKE8wdlJqBE2Sc6Nv\n" +
            "ClBWlx8eumRL+8dFGxv+vhOU/OELeipc8j/EN14OXVElebuFTE6dYoGU++51Z36h\n" +
            "b8fCgS+QxQu4e2o8B8dEdhhbszCEx0VGQRsGfC4NMwpYXUJ4n/OyHp4Lcp5Xo+Tc\n" +
            "uIIgnrARVxNZ0GPvdd2/k4JZIcVtaNZ+H396q3X7tJp//xdID+BjqKf0laICVpiY\n" +
            "XGC2trCn5uH8fB4hUSqnbyWTl8q3DcctXoXU088NaQKBgQDojHDf11VBMm1jxf6k\n" +
            "Vn+YnAcCeEt/KveCd+OIQyDqK5wd5F9M7LEpfkJ6RNyaLploVbJ9pjsC963+DVeW\n" +
            "BJOC6NfPnKKsTKOfZufpz65QGvoxviS0rgvG05BReMgQXLxq4UYeSFyp4gxHsBRj\n" +
            "vRlWRgReOl7bFGdbVvqPYkvGcwKBgQDQzD+frzIZBAzsFXX/NLyDvCuA4shOVyBA\n" +
            "j9x+6hedAds6cw4Pq4mMriozkxehBmajTLSKz4P3rSQX8I6XbszY5imi1LNtoTv1\n" +
            "TRMXHFLxXTOX9SLk5E/yyGVEGtMpXtx9xb0x23F6X+Wazz5Fi7jU3dNA7zeshr+V\n" +
            "vOe1tpnP1QKBgBFrO6U4rvUkPtdjZFCQ/hvDTC8eLaGH6WRHcK6rQJLp01bV3WGY\n" +
            "tSiDgZ1A/Tk7VlhfWvBYfEEbplfKTg45p4b9w/SUKgc38mWlQqfb25W6f5UsRfmW\n" +
            "s+Efxcakgm/fUmm7GBJ6nx5GhV71TIGwD27L7dXVD3ffjmJkIH6PAVgLAoGAVjSd\n" +
            "6B37PxzPAQHzT8TEOFeOVo5LSRzrsfwzeyrHSQmlK8KpAYs/JRgbUGPgk5kC81UO\n" +
            "DWmUAMQ5bLDjDl6usLaGZjYPRqoVCTgjM518GS7zyD9rgrlI0OIv/SheGmyR+FOV\n" +
            "w2noti+1qm76NNh1HV3ghKS/hbm0i+wMdpfHdWkCgYEA1HO21LCd5RUT4I8kcfBX\n" +
            "mkVafNNWThjxoXgLwJlV1bUSDy6zCzZ1wABbZVd+7D4qHIdrHuQGL1gQgmDYIQ2O\n" +
            "SvcXKtw8ce5/93QQtGhbzFRxkO6Jsi9dk/tGvAaLvMSxlGK/965ERZg2NTRSPFy4\n" +
            "RCIbXUJ77TbUN8vWvOwPCC4=";

    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvaula297kT4a7yOeUi6kU8wN77MVjfIFdsnm5PVWG4MltLtMVYWnlyteukPcGGsNZXXD8CJuM7D4ZIqevHeD76hjejj3feuqOHsxxvmBbFcUno1HkJYnhNwx6bFyg5ypPWvXDXwFsuQHJkGw5lGEDfJRZ5ZzCmZI36T";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.gzyhts.com:8081/app/alipay/notify_url";

    //5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://www.xxx.com/alipay/return_url.do";

    // 6.请求支付宝的网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";

    // 7.编码
    public static String CHARSET = "UTF-8";

    // 8.返回格式
    public static String FORMAT = "json";

    // 9.加密类型
    public static String SIGNTYPE = "RSA2";
}
