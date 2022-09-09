package com.cognizant.mfpe.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UsernameNotFoundException;



/**
 * The Class GlobalExceptionHandler.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Credential exception.
	 *
	 * @param exception the exception
	 * @return the response entity
	 */
	@ExceptionHandler(CredentialsException.class)
	public ResponseEntity<Object> credentialException(CredentialsException exception) {
		
		log.info("BEGIN   -   [Exception(credentialException)]");
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("timestamp", new Date());
		map.put("status", HttpStatus.UNAUTHORIZED.value());
		map.put("message", "Username and password doesn't match.");
		
		log.info("END   -   [Exception(credentialException)]");
		
		return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * Username not found exception.
	 *
	 * @param exception the exception
	 * @return the response entity
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> usernameNotFoundException(UsernameNotFoundException exception) {
		
		log.info("BEGIN   -   [Exception(UsernameNotFoundException)]");
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("timestamp", new Date());
		map.put("status", HttpStatus.UNAUTHORIZED.value());
		map.put("message", "Username not found");
		
		log.info("END   -   [Exception(UsernameNotFoundException)]");
		
		return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
	}

}