package com.mx.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by menxu on 18/6/28.
 */
@SpringBootApplication(scanBasePackages = "com.mx")
@EnableScheduling
public class WebapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebapiApplication.class, args);
    }
}
