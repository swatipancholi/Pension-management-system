package com.cognizant.mfpe.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * The Class GlobalExceptionHandlerTest.
 */
@SpringBootTest(classes = GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

	/** The global exception handler. */
	@Autowired
	private GlobalExceptionHandler globalExceptionHandler;

	/**
	 * Handles user name null exception test.
	 */
	@Test
	void handlesUserNameNullExceptionTest() {
		CredentialsException credentialsException = new CredentialsException("Wrong Credentials");
		globalExceptionHandler.credentialException(credentialsException);
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("timestamp", new Date());
		map.put("status", HttpStatus.UNAUTHORIZED.value());
		map.put("message", "Username and password doesn't match.");
		ResponseEntity<?> entity = new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCodeValue());
	}

	@Test
	void handlesUsernameNotFoundExceptionTest() {
		UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("Username is Invalid.");
		globalExceptionHandler.usernameNotFoundException(usernameNotFoundException);
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("timestamp", new Date());
		map.put("status", HttpStatus.UNAUTHORIZED.value());
		map.put("message", "Username is Invalid.");
		ResponseEntity<?> entity = new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
      assertEquals(401, entity.getStatusCodeValue());
	}
}