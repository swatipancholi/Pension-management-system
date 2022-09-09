package com.cognizant.mfpe.processpensionmicroservice.util;

import com.cognizant.mfpe.processpensionmicroservice.model.PensionerDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerInput;


/**
 * The Class ValidatePensionerDetails.
 */
public class ValidatePensionerDetails {

	/**
	 * Instantiates a new validate pensioner details.
	 */
	private ValidatePensionerDetails() {
		super();
	}

	/**
	 * Checks if is valid details.
	 *
	 * @param pensionerInput the pensioner input
	 * @param pensionerDetail the pensioner detail
	 * @return true, if is valid details
	 */
	public static boolean isValidDetails(PensionerInput pensionerInput, PensionerDetail pensionerDetail) {
		
		return (pensionerDetail.getPensionerName().equalsIgnoreCase(pensionerInput.getPensionerName())
				&& pensionerDetail.getPensionerDob().equals(pensionerInput.getPensionerDob())
				&& pensionerDetail.getPan().equalsIgnoreCase(pensionerInput.getPan())
				&& pensionerDetail.getPensionType().equalsIgnoreCase(pensionerInput.getPensionType()));
	}
}
