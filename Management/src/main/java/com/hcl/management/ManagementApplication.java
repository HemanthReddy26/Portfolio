package com.hcl.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
	
//	@Bean
//	   public Docket productApi() {
//	      return new Docket(DocumentationType.SWAGGER_2).select()
//	         .apis(RequestHandlerSelectors.basePackage("com.hcl.management")).build();
//	   }


}
