package com.cognizant.mfpe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The Class InvalidTokenException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidTokenException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3767828788299685166L;

	/**
	 * Instantiates a new invalid token exception.
	 *
	 * @param msg the msg
	 */
	public InvalidTokenException(String msg) {
		super(msg);
	}

}
