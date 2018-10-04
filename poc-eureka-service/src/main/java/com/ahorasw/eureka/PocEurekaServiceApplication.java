package com.ahorasw.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.netflix.appinfo.AmazonInfo;

@EnableEurekaServer
@SpringBootApplication
public class PocEurekaServiceApplication {

	public static void main(String[] args) {
		
		System.out.println("Started!!");
		SpringApplication.run(PocEurekaServiceApplication.class, args);
	}
	/*
	@Bean
	@Profile("prod")
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
	  EurekaInstanceConfigBean b = new EurekaInstanceConfigBean(inetUtils);
	  AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
	  b.setDataCenterInfo(info);
	  return b;
	}
	*/
}
