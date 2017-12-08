package com.springboot.dubbo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableConfigurationProperties
@ComponentScan({"com.springboot.dubbo"})
@ImportResource("classpath:dubbo.xml")
@Slf4j
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        log.info("web客户端启动!!!!");
        synchronized (WebApplication.class) {
            while (true) {
                try {
                    WebApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }
}
