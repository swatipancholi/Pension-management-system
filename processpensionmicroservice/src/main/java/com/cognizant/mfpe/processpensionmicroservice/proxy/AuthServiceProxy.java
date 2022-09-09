package com.cognizant.mfpe.processpensionmicroservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * The Interface AuthServiceProxy.
 */
@FeignClient(url = "http://localhost:8084/authorization", name = "authentication-service")
public interface AuthServiceProxy {

	/**
	 * Validate token.
	 *
	 * @param token the token
	 * @return true, if successful
	 */
	@GetMapping("/validate")
	public boolean validateToken(@RequestHeader(name = "Authorization", required = true) String token);

}
