package com.cognizant.mfpe.processpensionmicroservice.exception;


/**
 * The Class AadharNotFoundException.
 */
public class AadharNotFoundException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8094620206245086228L;
	
	/**
	 * Instantiates a new aadhar not found exception.
	 *
	 * @param msg the msg
	 */
	public AadharNotFoundException(String msg) {
		super(msg);
	}
}
