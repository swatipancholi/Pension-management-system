package com.cognizant.mfpe.processpensionmicroservice.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;


/** The Constant log. */

/** The Constant log. */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle invalid details entered exception.
     *
     * @param ex the ex
     * @return the response entity
     */
	@ExceptionHandler(InvalidDetailsEnteredException.class)
    protected ResponseEntity<ApiErrorResponse> handleInvalidDetailsEnteredException(InvalidDetailsEnteredException ex) {
    	
    	log.info("BEGIN   -   [Exception(DataIntegrityViolationException)]");
    	
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST); 
        errorResponse.setLocalizedMessage("Invalid Details Entered");
        errorResponse.setMessage("Invalid Details Entered");
        
        log.info("END   -   [Exception(DataIntegrityViolationException)]");
        
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handle invalid details entered exception.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(AadharNotFoundException.class)
    protected ResponseEntity<ApiErrorResponse> handleAadharNotFoundException(AadharNotFoundException ex) {
    	
    	log.info("BEGIN   -   [Exception(InvalidTokenException)]");
    	
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST,"Aadhar Not Found Exception",new Throwable()); 

        log.info("END   -   [Exception(InvalidTokenException)]");
        
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handle multiple pan exception.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ApiErrorResponse> handleMultiplePanException(DataIntegrityViolationException ex) {
    	
    	log.info("BEGIN   -   [Exception(DataIntegrityViolationException)]");
    	
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST); 
        errorResponse.setLocalizedMessage("Details already exist in the database");
        errorResponse.setMessage("Details already exist in the database");
        
        log.info("END   -   [Exception(DataIntegrityViolationException)]");
        
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handle invalid token.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(InvalidTokenException.class)
    protected ResponseEntity<ApiErrorResponse> handleInvalidToken(InvalidTokenException ex) {
    	
    	log.info("BEGIN   -   [Exception(InvalidTokenException)]");
    	
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.UNAUTHORIZED); 
        errorResponse.setLocalizedMessage("Token is invalid or expired");
        errorResponse.setMessage("Token is invalid or expired");
        
        log.info("END   -   [Exception(InvalidTokenException)]");
        
        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }

    
}
