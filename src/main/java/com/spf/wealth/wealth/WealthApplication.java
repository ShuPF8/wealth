package com.spf.wealth.wealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.spf.*")
public class WealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(WealthApplication.class, args);
    }
}
