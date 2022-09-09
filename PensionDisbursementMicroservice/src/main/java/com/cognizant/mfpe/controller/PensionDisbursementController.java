package com.cognizant.mfpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.mfpe.exception.TokenInvalidException;
import com.cognizant.mfpe.model.ProcessPensionInput;
import com.cognizant.mfpe.service.PensionDisbursementService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class PensionDisbursementController.
 */
@RestController

/** The Constant log. */
@Slf4j
@CrossOrigin
public class PensionDisbursementController {

	/** The pension disbursement service. */
	@Autowired
	private PensionDisbursementService pensionDisbursementService;

	/**
	 * Gets the pension disbursement.
	 *
	 * @param token the token
	 * @param processPensionInput the process pension input
	 * @return the pension disbursement
	 * @throws TokenInvalidException the token invalid exception
	 */
	@PostMapping("/disbursepension")
	@ApiOperation(value = "getPensionDisbursement", notes = "returns success code or failure code as Integer", httpMethod = "POST", response = Integer.class)
	public Integer getPensionDisbursement(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput) throws TokenInvalidException {
		log.info("Begin - [getPensionDisbursement()]");
		Integer returnCode = pensionDisbursementService.getPensionDisbursement(token, processPensionInput);
		log.debug("Return Code :=" + returnCode);
		log.info("END - [getPensionDisbursement()]");
		return returnCode;

	}


}