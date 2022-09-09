package com.cognizant.mfpe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;


/**
 * The Class GlobalExceptionHandler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle invalid pensioner exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(FeignException.class)
	protected ResponseEntity<ApiErrorResponse> handleInvalidPensionerException(FeignException ex) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND);
		errorResponse.setLocalizedMessage("Aadhaar Number doesn't exist in the database.");
		errorResponse.setMessage("Aadhaar Number doesn't exist in the database.");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle invalid token.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(TokenInvalidException.class)
	protected ResponseEntity<ApiErrorResponse> handleInvalidToken(TokenInvalidException ex) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.UNAUTHORIZED);
		errorResponse.setLocalizedMessage("Token is invalid or expired");
		errorResponse.setMessage("Token is invalid or expired");
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
}
