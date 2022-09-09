package com.cognizant.mfpe.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.mfpe.model.PensionerDetail;


/**
 * The Interface PensionerDetailClient.
 */
@FeignClient(name = "pensioner-detail-microservice", url = "")
public interface PensionerDetailClient {

	/**
	 * Gets the pensioner details.
	 *
	 * @param token the token
	 * @param aadharNumber the aadhar number
	 * @return the pensioner details
	 */
	@GetMapping("/{aadhaarNo}")
	PensionerDetail getPensionerDetails(@RequestHeader(name = "Authorization") String token,
			@PathVariable("aadhaarNo") String aadharNumber);

}