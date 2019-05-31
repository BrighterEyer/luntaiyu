package com.zhangzhao.common.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 图片绝对地址与虚拟地址映射
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private UploadConfig uploadConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("/icon/**").addResourceLocations("file:" + uploadConfig.getIconFileUploadPath() + "/");
        registry.addResourceHandler("/zhangzhaocarImg/**").addResourceLocations("file:" + uploadConfig.getHouseFileUploadPath() + "/");
        registry.addResourceHandler("/advertisement/**").addResourceLocations("file:" + uploadConfig.getAdvertisementFileUploadPath() + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login.html");
        registry.addViewController("/login").setViewName("login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
