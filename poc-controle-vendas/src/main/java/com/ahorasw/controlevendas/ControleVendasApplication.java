package com.ahorasw.controlevendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class ControleVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleVendasApplication.class, args);
	}


}

