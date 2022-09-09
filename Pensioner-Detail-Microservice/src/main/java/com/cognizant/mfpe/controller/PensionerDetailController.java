package com.cognizant.mfpe.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.mfpe.exception.AadharNotFoundException;
import com.cognizant.mfpe.exception.InvalidTokenException;
import com.cognizant.mfpe.model.PensionerDetail;
import com.cognizant.mfpe.proxy.AuthServiceProxy;
import com.cognizant.mfpe.service.PensionerDetailService;

import feign.FeignException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class PensionerDetailController.
 */
@RestController

/** The Constant log. */
@Slf4j
@Api(value = "Pensioner Detail resource REST endpoint")
@CrossOrigin
public class PensionerDetailController {

	/** The pensioner detail service. */
	@Autowired
	private PensionerDetailService pensionerDetailService;
	
	/** The auth service proxy. */
	@Autowired
	private AuthServiceProxy authServiceProxy;

	/**
	 * Fetch pensioner detail by aadhaar.
	 *
	 * @param token the token
	 * @param aadhaarNo the aadhaar no
	 * @return the pensioner detail
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@GetMapping({ "/aadhaar/{aadhaarNo}" })
	@ApiOperation(value = "checkPensionerAadhaar", notes = "returns pensionerDetail object conatining all the information available in database", httpMethod = "GET", response = PensionerDetail.class)
	public PensionerDetail fetchPensionerDetailByAadhaar(@RequestHeader(name = "Authorization") String token,
			@ApiParam(name = "aadhaarNo", value = "Aadhaar number of the Pensioner.") @PathVariable String aadhaarNo)
			throws  InvalidTokenException, AadharNotFoundException, IOException, ParseException {
		try {
			if (!authServiceProxy.validateToken(token)) {
				throw new InvalidTokenException("Token is Invalid");
			}
		} catch (FeignException e) {
			throw new InvalidTokenException("Token is Invalid or Expired");
		}
		PensionerDetail pensionerDetail = pensionerDetailService.findAadhaar(aadhaarNo);
		
		log.debug("Pensioner Detail  := " + pensionerDetail);
		log.info("END   -   [fetchPensionerDetailByAadhaar()-controller]");
		return pensionerDetail;
	}

}