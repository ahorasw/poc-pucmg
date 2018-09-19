package com.ahorasw.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PocEurekaServiceApplication {

	public static void main(String[] args) {
		
		System.out.println("Started!!");
		SpringApplication.run(PocEurekaServiceApplication.class, args);
	}
}
