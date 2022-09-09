package com.cognizant.mfpe.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

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
	 */
	public ApiErrorResponse(HttpStatus httpStatus) {
		this();
		this.httpStatus = httpStatus;
	}

}
