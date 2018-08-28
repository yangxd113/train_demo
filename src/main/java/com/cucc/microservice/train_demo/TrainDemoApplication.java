package com.cucc.microservice.train_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TrainDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainDemoApplication.class, args);
		System.out.println("进入traindemo");
	}
}


