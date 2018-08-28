package com.cucc.microservice.train_demo.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    /**
     * 设置监控路径,默认监控com.cbss
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.cucc.microservice"))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * ApiInfo
     */
    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("train_demo")
                .version("1.0.0")
                .build();
    }

}
