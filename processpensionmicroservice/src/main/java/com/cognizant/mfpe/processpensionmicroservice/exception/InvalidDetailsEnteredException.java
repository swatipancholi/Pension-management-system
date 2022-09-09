package com.cognizant.mfpe.processpensionmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The Class InvalidDetailsEnteredException.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDetailsEnteredException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2226494044851320687L;

	/**
	 * Instantiates a new invalid details entered exception.
	 *
	 * @param msg the msg
	 */
	public InvalidDetailsEnteredException(String msg) {
		super(msg);
	}

}
