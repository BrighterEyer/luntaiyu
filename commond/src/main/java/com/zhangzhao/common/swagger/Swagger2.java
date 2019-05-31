package com.zhangzhao.common.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger2 {
    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
//		tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        tokenPar.name("refresh_token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_12)
                .groupName("createRestApi")
//				.host("101.201.69.128:8081")
                .apiInfo(apiInfo("App-api文档", "Restful API文档", "1.0"))
                .enable(enableSwagger)
//                .tags(new Tag("BankCard", "1 银行卡"))
//                .tags(new Tag("Complaints", "2 投诉"))
//                .tags(new Tag("MemberCenter", "3 会员"))
//                .tags(new Tag("OrderSupply", "4 订单"))
//				.tags(new Tag("SlideshowImg", "5 广告轮播图 "))
//				.tags(new Tag("Messages", "6 系统消息"))
//				.tags(new Tag("TransactionRecord", "7 交易记录"))
//				.tags(new Tag("User", "8 用户"))
//				.tags(new Tag("Vehicle", "9 车辆"))
//				.tags(new Tag("Wallet", "10 钱包"))
//				.tags(new Tag("User", "11 用户相关"))
//				.tags(new Tag("Supply", "12 货源相关"))
//				.tags(new Tag("AliPay", "13 支付宝支付"))
//				.tags(new Tag("CompanyProfile", "14 公司简介"))
//				.tags(new Tag("Evaluate", "15 评价"))
//				.tags(new Tag("WeiXin", "16 微信"))
//				.tags(new Tag("ADedicatedLine", "17 专线信息"))
//				.tags(new Tag("PracticeCar", "18 熟车"))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.zhangzhao.app.controller"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars);
	}

//	@Bean
//	public Docket createAdminRestApi() {
//		return new Docket(DocumentationType.SPRING_WEB)
//                .groupName("createAdminRestApi")
//                .apiInfo(apiInfo("Admin-api文档","Restful API文档","1.0"))
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.adminController"))
//				.paths(PathSelectors.any())
//				.build();
//	}
	
	private ApiInfo apiInfo(String name,String description,String version) {
		return new ApiInfoBuilder()
				.title(name)
				.description(description)
				.termsOfServiceUrl("NO terms of service")
				.contact(new Contact("zzq", "******", "1072286067@qq.com"))//作者
				.version(version)
				.build();
	}
}
