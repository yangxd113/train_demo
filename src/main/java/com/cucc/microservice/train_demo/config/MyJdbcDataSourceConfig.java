package com.cucc.microservice.train_demo.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MyJdbcDataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.mysqlone")
    DataSource mysqlDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
}
