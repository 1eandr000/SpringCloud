package com.leandro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceOpenfeign8092Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOpenfeign8092Application.class, args);
    }

}
