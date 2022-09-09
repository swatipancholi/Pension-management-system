package com.cognizant.mfpe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import lombok.extern.slf4j.Slf4j;


/**
 * The Class GlobalExceptionHandler.
 */
@RestControllerAdvice

/** The Constant log. */
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Handle invalid token.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(InvalidTokenException.class)
	protected ResponseEntity<ApiErrorResponse> handleInvalidToken(InvalidTokenException ex) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
	
	 /**
 	 * Handle aadhar not found exception.
 	 *
 	 * @param ex the ex
 	 * @return the response entity
 	 */
 	@ExceptionHandler(AadharNotFoundException.class)
	 protected ResponseEntity<ApiErrorResponse> handleAadharNotFoundException(AadharNotFoundException ex) {
	    	
	    	log.info("BEGIN   -   [Exception(AadharNotFoundException)]");
	    	
	        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND); 
	        errorResponse.setLocalizedMessage(ex.getLocalizedMessage());
	        errorResponse.setMessage(ex.getMessage());
	        
	        log.info("END   -   [Exception(AadharNotFoundException)]");
	        
	        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	    }

}
