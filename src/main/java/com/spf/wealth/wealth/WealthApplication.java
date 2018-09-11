package com.spf.wealth.wealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = "com.spf.*")
@EnableScheduling
public class WealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(WealthApplication.class, args);
    }
}
