package com.spf.wealth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.spf.wealth.mapper")
@SpringBootApplication(scanBasePackages = "com.spf.*")
@EnableTransactionManagement
public class WealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(WealthApplication.class, args);
    }
}
