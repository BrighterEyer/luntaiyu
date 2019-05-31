package com.zhangzhao.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by zzq on 18/6/15.
 */
@Component
@Configuration
//@PropertySource(value = {"classpath:application-upload.properties"})
public class UploadConfig {

    /* 基础路径 */
    @Value("${basePath}")
    private String basePath;

    /* 房屋图片路径 */
    @Value("${houseImg}")
    private String houseImg;

    /*头像路径 */
    @Value("${icon}")
    private String icon;

    /*广告路径 */
    @Value("${advertisement}")
    private String advertisement;

    /*项目上传路径*/
    @Value("${uploadPath}")
    private String uploadPath;

    //默认头像
    private String defaultIcon = "http://t.cn/RCzsdCq";

    /*头像上传路径以及保存路径*/
    private String iconFileUploadPath;
    private String iconFileSavePath;//用户文件路径在数据库中保存的路径

    private String houseFileUploadPath = getBasePath() + getUploadPath() + getHouseImg();
    private String houseFileSavePath = houseImg;

    /*广告图片上传路径以及保存路径*/
    private String advertisementFileUploadPath = getBasePath() + getUploadPath() + getAdvertisement();
    private String advertisementFileSavePath = advertisement;

    @PostConstruct
    public void init() {
        iconFileUploadPath = getBasePath() + getUploadPath() + getIcon();
        iconFileSavePath = icon;
        houseFileUploadPath = getBasePath() + getUploadPath() + getHouseImg();
        houseFileSavePath = houseImg;
        advertisementFileUploadPath = getBasePath() + getUploadPath() + getAdvertisement();
        advertisementFileSavePath = advertisement;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getHouseImg() {
        return houseImg;
    }

    public void setHouseImg(String houseImg) {
        this.houseImg = houseImg;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getDefaultIcon() {
        return defaultIcon;
    }

    public void setDefaultIcon(String defaultIcon) {
        this.defaultIcon = defaultIcon;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getIconFileUploadPath() {
        return iconFileUploadPath;
    }

    public void setIconFileUploadPath(String iconFileUploadPath) {
        this.iconFileUploadPath = iconFileUploadPath;
    }

    public String getIconFileSavePath() {
        return iconFileSavePath;
    }

    public void setIconFileSavePath(String iconFileSavePath) {
        this.iconFileSavePath = iconFileSavePath;
    }

    public String getHouseFileUploadPath() {
        return houseFileUploadPath;
    }

    public void setHouseFileUploadPath(String houseFileUploadPath) {
        this.houseFileUploadPath = houseFileUploadPath;
    }

    public String getHouseFileSavePath() {
        return houseFileSavePath;
    }

    public void setHouseFileSavePath(String houseFileSavePath) {
        this.houseFileSavePath = houseImg;
    }

    public String getAdvertisementFileUploadPath() {
        return advertisementFileUploadPath;
    }

    public void setAdvertisementFileUploadPath(String advertisementFileUploadPath) {
        this.advertisementFileUploadPath = advertisementFileUploadPath;
    }

    public String getAdvertisementFileSavePath() {
        return advertisementFileSavePath;
    }

    public void setAdvertisementFileSavePath(String advertisementFileSavePath) {
        this.advertisementFileSavePath = advertisementFileSavePath;
    }
}
