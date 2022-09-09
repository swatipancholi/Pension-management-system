package com.cognizant.mfpe.processpensionmicroservice.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionTest.
 */
class GlobalExceptionTest {

/** The exception. */
GlobalExceptionHandler exception = new GlobalExceptionHandler();
	
	/** The data integrity violation exception. */
	@Autowired
	DataIntegrityViolationException dataIntegrityViolationException;
	
	/** The token exception. */
	@Autowired
	InvalidTokenException tokenException;
	
	/** The aadhar not found exception. */
	@Autowired
	AadharNotFoundException aadharNotFoundException;
	
	/** The invalid details entered exception. */
	@Autowired
	InvalidDetailsEnteredException invalidDetailsEnteredException;
	
	/**
	 * Data integrity violation exception test.
	 */
	@Test
	public void dataIntegrityViolationExceptionTest()
	{
		ResponseEntity<ApiErrorResponse> result = exception.handleMultiplePanException(dataIntegrityViolationException);
		assertEquals(400, result.getStatusCodeValue());
	}
	
	/**
	 * Token exception test.
	 */
	@Test
	public void tokenExceptionTest()
	{
		ResponseEntity<ApiErrorResponse> result = exception.handleInvalidToken(tokenException);
		assertEquals(401, result.getStatusCodeValue());
	}
	
	/**
	 * Aadhar not found exception test.
	 */
	@Test
	public void aadharNotFoundExceptionTest()
	{
		ResponseEntity<ApiErrorResponse> result = exception.handleAadharNotFoundException(aadharNotFoundException);
		assertEquals(400, result.getStatusCodeValue());
	}
	
	/**
	 * Invalid details entered exception test.
	 */
	@Test
	public void invalidDetailsEnteredExceptionTest()
	{
		ResponseEntity<ApiErrorResponse> result = exception.handleInvalidDetailsEnteredException(invalidDetailsEnteredException);
		assertEquals(400, result.getStatusCodeValue());
	}
	
}
