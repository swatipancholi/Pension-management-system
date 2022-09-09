package com.cognizant.mfpe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The Class TokenInvalidException.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class TokenInvalidException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3767828788299685166L;

	/**
	 * Instantiates a new token invalid exception.
	 *
	 * @param msg the msg
	 */
	public TokenInvalidException(String msg) {
		super(msg);
	}
}
