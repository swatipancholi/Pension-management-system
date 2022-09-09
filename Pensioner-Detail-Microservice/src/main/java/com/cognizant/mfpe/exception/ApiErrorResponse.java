package com.cognizant.mfpe.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


/**
 * Gets the localized message.
 *
 * @return the localized message
 */
@Getter

/**
 * Sets the localized message.
 *
 * @param localizedMessage the new localized message
 */
@Setter
public class ApiErrorResponse {

	/** The http status. */
	private HttpStatus httpStatus;

	/** The time stamp. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;

	/** The message. */
	private String message;

	/** The localized message. */
	private String localizedMessage;

	/**
	 * Instantiates a new api error response.
	 */
	public ApiErrorResponse() {
		timeStamp = LocalDateTime.now();
	}

	/**
	 * Instantiates a new api error response.
	 *
	 * @param httpStatus the http status
	 * @param message the message
	 * @param t the t
	 */
	public ApiErrorResponse(HttpStatus httpStatus, String message, Throwable t) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
		this.localizedMessage = t.getLocalizedMessage();
	}
	
	 /**
 	 * Instantiates a new api error response.
 	 *
 	 * @param httpStatus the http status
 	 */
 	public ApiErrorResponse(HttpStatus httpStatus) {
	        this();
	        this.httpStatus = httpStatus;
	    }
}
