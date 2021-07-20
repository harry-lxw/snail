package com.winter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value="com.winter.mapper")
public class SnailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnailApplication.class, args);
    }

}
