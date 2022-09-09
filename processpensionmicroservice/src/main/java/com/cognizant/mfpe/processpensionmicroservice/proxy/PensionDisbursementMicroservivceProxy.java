package com.cognizant.mfpe.processpensionmicroservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.mfpe.processpensionmicroservice.model.ProcessPensionInput;


/**
 * The Interface PensionDisbursementMicroservivceProxy.
 */
@FeignClient(name = "pension-disbursement-service", url = "localhost:8200/pension-disbursement-service")
public interface PensionDisbursementMicroservivceProxy {

	/**
	 * Gets the pension disbursement.
	 *
	 * @param token the token
	 * @param processPensionInput the process pension input
	 * @return the pension disbursement
	 */
	@PostMapping("/disbursepension")
	public Integer getPensionDisbursement(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput);

}
