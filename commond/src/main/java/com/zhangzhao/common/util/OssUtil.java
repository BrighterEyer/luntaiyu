package com.zhangzhao.common.util;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyun.oss.common.utils.LogUtils;
import com.aliyun.oss.model.BucketInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Slf4j
public class OssUtil {

    private volatile static OSSClient instance;

    //OSS 的地址
    private static String endpoint = "";
    //OSS 的key值
    private static String accessKeyId = "";
    //OSS 的secret值
    private static String accessKeySecret = "";
    //OSS 的bucket名字
    private static String bucketName = "zhangzhao";
    //设置URL过期时间为10年
    private final static Date OSS_URL_EXPIRATION = DateUtils.addDays(new Date(), 365 * 10);

    /**
     * 判断是否存在bucketName
     * @return
     */
    private static boolean hasBucket(OSSClient ossClient){
        return ossClient.doesBucketExist(bucketName);
    }

    /**
     * 创建bucket实例
     */
    private static void creatBucket(OSSClient ossClient){
        if(!hasBucket(ossClient)){
            try {
                ossClient.createBucket(bucketName);
            } catch (Exception e) {
                log.error("{}", "创建Bucket失败,请核对Bucket名称(规则：只能包含小写字母、数字和短横线，必须以小写字母和数字开头和结尾，长度在3-63之间)");
                throw new RuntimeException("创建Bucket失败,请核对Bucket名称(规则：只能包含小写字母、数字和短横线，必须以小写字母和数字开头和结尾，长度在3-63之间)");
            }
        }
    }

    /**
     * 获取ossClient
     * @return
     */
    public static OSSClient ossClientInitialization(){
        // 创建ClientConfiguration。ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
        ClientConfiguration conf = new ClientConfiguration();
        // 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
        conf.setMaxConnections(200);
        // 设置Socket层传输数据的超时时间，默认为50000毫秒。
        conf.setSocketTimeout(10000);
        // 设置建立连接的超时时间，默认为50000毫秒。
        conf.setConnectionTimeout(10000);
        // 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
        conf.setConnectionRequestTimeout(1000);
        // 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
        conf.setIdleConnectionTime(10000);
        // 设置失败请求重试次数，默认为3次。
        conf.setMaxErrorRetry(5);
        // 设置是否支持将自定义域名作为Endpoint，默认支持。
        conf.setSupportCname(true);
        // 设置是否开启二级域名的访问方式，默认不开启。
        conf.setSLDEnabled(true);
        // 设置连接OSS所使用的协议（HTTP/HTTPS），默认为HTTP。
        conf.setProtocol(Protocol.HTTP);
        // 设置用户代理，指HTTP的User-Agent头，默认为aliyun-sdk-java。
        //conf.setUserAgent("aliyun-sdk-java");
        // 设置代理服务器端口。
        //conf.setProxyHost("<yourProxyHost>");
        // 设置代理服务器验证的用户名。
        //conf.setProxyUsername("<yourProxyUserName>");
        // 设置代理服务器验证的密码。
        //conf.setProxyPassword("<yourProxyPassword>");
        // 创建OSSClient实例。
        if (instance == null) {
            synchronized (OssUtil.class) {
                if (instance == null) {
                    instance = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
                }
            }
        }
        return instance;
    }

    /**
     * 释放ossClient资源
     */
    private static void ossClientRelease(OSSClient ossClient){
        ossClient.shutdown();
    }

    /**
     * 获取bucket信息
     */
    private BucketInfo getBucketInfo(OSSClient ossClient){
        return ossClient.getBucketInfo(bucketName);
    }

    /**
     * 文件上传并返回文件路径
     * @param file
     * @return
     * @throws IOException
     */
    public static String fileUpload(MultipartFile file) throws IOException {
        OSSClient ossClient = ossClientInitialization();
        creatBucket(ossClient);
        String fileName = getFileName(file.getOriginalFilename());
        // 上传文件流
        InputStream inputStream = file.getInputStream();
        ossClient.putObject(bucketName, fileName, inputStream);
        String url = getUrl(ossClient,fileName);
        ossClientRelease(ossClient);
        return url;
    }

    /**
     * 获取附件上传保存到服务器的名称
     * @param fileName    文件原始名称
     * @return
     */
    public static String getFileName(String fileName){
        if(StringUtils.isBlank(fileName)){
            return null;
        }
        if(fileName.lastIndexOf(".")>-1){
            fileName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        }else{
            fileName = "."+fileName.toLowerCase();
        }
        Date date = new Date();
        long t = date.getTime();
        return String.valueOf(t)+fileName;
    }


    /**
     * 获得url链接
     *
     * @param fileName
     * @return
     */
    public static String getUrl(OSSClient ossClient,String fileName) {
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, OSS_URL_EXPIRATION);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
