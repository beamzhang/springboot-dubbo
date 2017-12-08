package com.springboot.dubbo.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan({"com.springboot.dubbo"})
@ImportResource("classpath:dubbo.xml")
@Slf4j
public class UserApplication implements CommandLineRunner {

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.setWebEnvironment(false);
        app.run(args);
        log.info("user service 已经启动!!!");
        synchronized (UserApplication.class) {
            while (true) {
                try {
                    UserApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
