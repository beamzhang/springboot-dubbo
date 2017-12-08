package com.springboot.dubbo.web.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
//    @Bean
//    public Docket createRestApi() {
//        ParameterBuilder builder = new ParameterBuilder();
//        builder.name("Authentication").description("登录后需要携带token，获取图片验证码、登录、注册、激活不需要携带").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        List<Parameter> parameters = new ArrayList<>();
//        parameters.add(builder.build());
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .useDefaultResponseMessages(false)
//                .globalOperationParameters(parameters)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.springboot.dubbo.web.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//    
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("JavaWeb项目骨架")
//                .description("集成了SpringBoot、SpringMVC、Spring、MyBaits、MyBatis Generator、MyBatis PageHelper、Druid、Lombok、JWT、Spring Security、JavaMail、Thymeleaf、HttpClient、FileUpload、Spring Scheduler、Hibernate Validator、Redis、Spring Async、Spring Cache、Swagger、Spring Test、MockMvc、HTTPS、Spring DevTools热部署、Logback多环境日志、国际化、(WebSocket、RabbitMQ)，REST风格的接口的Web项目")
//                .termsOfServiceUrl("https://github.com/songxinjianqwe")
//                .contact(new Contact("SinjinSong", "https://github.com/beamzhang", "8691828197@163.com"))
//                .version("0.1")
//                .build();
//    }
    
//    /**
//	 * 
//	 * @return
//	 */
//	@Bean
//	public Docket accessToken() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("api")// 定义组
//				.select() // 选择那些路径和api会生成document
//				.apis(RequestHandlerSelectors.basePackage("com.springboot.dubbo.web.controller.user")) // 拦截的包路径
//				.paths(PathSelectors.any())// 拦截的接口路径
//				.build() // 创建
//				.apiInfo(apiInfo()); // 配置说明
//	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("文思海辉")// 标题
				.description("spring boot 学习")// 描述
				.termsOfServiceUrl("http://www.pactera.com")//
				.contact(new Contact("张强", "http://www.pactera.com", "52201392@qq.com"))// 联系
				.version("1.0")// 版本
				.build();
	}
}
