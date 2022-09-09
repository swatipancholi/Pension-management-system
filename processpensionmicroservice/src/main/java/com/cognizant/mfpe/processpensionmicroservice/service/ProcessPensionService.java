package com.cognizant.mfpe.processpensionmicroservice.service;

import com.cognizant.mfpe.processpensionmicroservice.exception.AadharNotFoundException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidDetailsEnteredException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidTokenException;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerInput;
import com.cognizant.mfpe.processpensionmicroservice.model.ProcessPensionInput;


/**
 * The Interface ProcessPensionService.
 */
public interface ProcessPensionService {

	/**
	 * Gets the pension details.
	 *
	 * @param token the token
	 * @param pensionerInput the pensioner input
	 * @return the pension details
	 * @throws InvalidDetailsEnteredException the invalid details entered exception
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	public PensionDetail getPensionDetails(String token, PensionerInput pensionerInput)
			throws InvalidDetailsEnteredException, InvalidTokenException, AadharNotFoundException;
	
	/**
	 * Gets the response.
	 *
	 * @param token the token
	 * @param processPensionInput the process pension input
	 * @return the response
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	public Integer getResponse(String token, ProcessPensionInput processPensionInput) throws InvalidTokenException, AadharNotFoundException;
}
