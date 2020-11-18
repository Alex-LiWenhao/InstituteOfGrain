package com.alex.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisPlusConfig
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/8 16:13
 * @Version 1.0
 */
@Configuration
@MapperScan("com.alex.eduservice.mapper")
public class MyBatisPlusConfig {

    @Bean
    public ISqlInjector sqlInjector(){
        return  new LogicSqlInjector();
    }


    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
