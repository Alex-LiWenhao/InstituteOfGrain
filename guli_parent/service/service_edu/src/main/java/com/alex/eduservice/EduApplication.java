package com.alex.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName EduApplication
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/8 16:13
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.alex"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
