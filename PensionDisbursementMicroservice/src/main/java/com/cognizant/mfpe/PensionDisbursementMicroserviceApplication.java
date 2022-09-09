package com.cognizant.mfpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * The Class PensionDisbursementMicroserviceApplication.
 */
@EnableFeignClients("com.cognizant.mfpe")
@SpringBootApplication
@EnableDiscoveryClient
public class PensionDisbursementMicroserviceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PensionDisbursementMicroserviceApplication.class, args);
	}
}
