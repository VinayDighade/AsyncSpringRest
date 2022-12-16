package com.example.asyncrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AsyncRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncRestApplication.class, args);
    }

}
