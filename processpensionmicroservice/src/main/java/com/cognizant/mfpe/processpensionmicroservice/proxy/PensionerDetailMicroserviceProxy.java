package com.cognizant.mfpe.processpensionmicroservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.mfpe.processpensionmicroservice.model.PensionerDetail;


/**
 * The Interface PensionerDetailMicroserviceProxy.
 */
@FeignClient(name = "pensioner-detail-service", url = "localhost:8100/PensionerDetailByAadhaar")
public interface PensionerDetailMicroserviceProxy {

	/**
	 * Fetch pensioner detai by aadhaar.
	 *
	 * @param token the token
	 * @param aadhaarNo the aadhaar no
	 * @return the pensioner detail
	 */
	@GetMapping("/{aadhaarNo}")
	public PensionerDetail fetchPensionerDetailByAadhaar(@RequestHeader(name = "Authorization") String token,
			@PathVariable String aadhaarNo);

}
