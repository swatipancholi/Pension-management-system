package com.cognizant.mfpe.service;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.cognizant.mfpe.exception.AadharNotFoundException;
import com.cognizant.mfpe.exception.InvalidTokenException;
import com.cognizant.mfpe.model.PensionerDetail;


/**
 * The Interface PensionerDetailService.
 */
@Service
public interface PensionerDetailService {

	/**
	 * Find aadhaar.
	 *
	 * @param aadhaarNo the aadhaar no
	 * @return the pensioner detail
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public PensionerDetail findAadhaar(String aadhaarNo) throws InvalidTokenException, AadharNotFoundException, IOException, ParseException;

}
