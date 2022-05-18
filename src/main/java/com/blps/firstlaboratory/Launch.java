package com.blps.firstlaboratory;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@EnableSwagger2
@EnableProcessApplication
public class Launch {

	public static void main(String[] args) {
		SpringApplication.run(Launch.class, args);
	}
}
