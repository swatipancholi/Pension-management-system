package com.cognizant.mfpe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The Class AadharNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
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
