package com.ahorasw.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class FornecedorStubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FornecedorStubApplication.class, args);
	}
}
