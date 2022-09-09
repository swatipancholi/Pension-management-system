package com.cognizant.mfpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * The Class PensionerDetailMicroserviceApplication.
 */
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients("com.cognizant.mfpe")
@EnableDiscoveryClient
public class PensionerDetailMicroserviceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PensionerDetailMicroserviceApplication.class, args);

	}

}
