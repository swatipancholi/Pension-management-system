package com.cognizant.mfpe.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.mfpe.exception.TokenInvalidException;


/**
 * The Interface AuthServiceProxy.
 */
@FeignClient(url = "", name = "process-pension-microservice")
public interface AuthServiceProxy {

	/**
	 * Validate token.
	 *
	 * @param token the token
	 * @return true, if successful
	 * @throws TokenInvalidException the token invalid exception
	 */
	@GetMapping("/validate")
	public boolean validateToken(@RequestHeader(name = "Authorization", required = true) String token)
			throws TokenInvalidException;

}
