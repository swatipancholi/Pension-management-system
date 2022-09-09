package com.cognizant.mfpe.processpensionmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.mfpe.processpensionmicroservice.exception.AadharNotFoundException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidDetailsEnteredException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidTokenException;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerInput;
import com.cognizant.mfpe.processpensionmicroservice.model.ProcessPensionInput;
import com.cognizant.mfpe.processpensionmicroservice.service.PensionDetailsServiceImpl;
import com.cognizant.mfpe.processpensionmicroservice.service.ProcessPensionServiceImpl;

import feign.FeignException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class ProcessPensionController.
 */
@RestController

/** The Constant log. */

/** The Constant log. */
@Slf4j
@Api(value = "Process Pension Controller REST endpoint")
@CrossOrigin
public class ProcessPensionController {

	/** The pension detail service. */
	@Autowired
	private PensionDetailsServiceImpl pensionDetailService;

	/** The process pension service. */
	@Autowired
	private ProcessPensionServiceImpl processPensionService;

	/**
	 * Gets the pension details.
	 *
	 * @param token the token
	 * @param pensionerInput the pensioner input
	 * @return the pension details
	 * @throws InvalidDetailsEnteredException the invalid details entered exception
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 * @throws FeignException the feign exception
	 */
	@PostMapping("/PensionDetail")
	@ApiOperation(value = "getPensionDetails", notes = "Validates user details using PensionerDetail microservice and returns details of the user along with the calculated pension amount after", httpMethod = "POST", response = PensionDetail.class)
	public PensionDetail getPensionDetails(@RequestHeader(name = "Authorization") String token,
			@ApiParam(name = "pensionerInput", value = "Details of the Pensioner from UI") @RequestBody PensionerInput pensionerInput)
			throws InvalidDetailsEnteredException, InvalidTokenException, AadharNotFoundException{

		log.info("BEGIN   -   [getPensionDetails()]-controller");
      
		PensionDetail pensionDetail = processPensionService.getPensionDetails(token, pensionerInput);
		
		log.debug("PensionDetail : " + pensionDetail);

		log.info("END   -   [getPensionDetails()]");

		return pensionDetailService.save(pensionDetail);
	}

	/**
	 * Process pension.
	 *
	 * @param token the token
	 * @param processPensionInput the process pension input
	 * @return the integer
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@PostMapping("/ProcessPension")
	@ApiOperation(value = "getPensionDetails", notes = "Invokes Pension disbursement microservice and returns the appropriate process code", httpMethod = "POST")
	public Integer processPension(@RequestHeader(name = "Authorization") String token,
			@ApiParam(name = "processPensionInput", value = "Aadhar and Pension Details of the Pensioner") @RequestBody ProcessPensionInput processPensionInput)
			throws InvalidTokenException, AadharNotFoundException {

		log.info("BEGIN   -   [processPension()]-contorller");
		return processPensionService.getResponse(token, processPensionInput);
	}
}
