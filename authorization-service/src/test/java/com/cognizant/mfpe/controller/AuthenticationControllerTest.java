package com.cognizant.mfpe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.mfpe.exception.CredentialsException;
import com.cognizant.mfpe.model.UserLogin;
import com.cognizant.mfpe.repository.UserRepository;
import com.cognizant.mfpe.service.JwtUtil;
import com.cognizant.mfpe.service.UserService;

import io.jsonwebtoken.SignatureException;


/**
 * The Class AuthenticationControllerTest.
 */
@SpringBootTest
class AuthenticationControllerTest {

	/** The authentication controller. */
	@InjectMocks
	private AuthorizationController authenticationController;

	/** The jwt util. */
	@Mock
	private JwtUtil jwtUtil;

	/** The user service. */
	@Mock
	private UserService userService;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/**
	 * Test valid login.
	 *
	 * @throws CredentialsException the credentials exception
	 */
	@Test
	void testValidLogin() throws CredentialsException {
		UserLogin user = new UserLogin("user1", "user1");
		UserDetails userDetails = new User(user.getUserName(), user.getPassword(), new ArrayList<>());

		when(userService.loadUserByUsername("user1")).thenReturn(userDetails);
		when(jwtUtil.generateToken(userDetails)).thenReturn("dummy-token");

		ResponseEntity<Object> userToken = authenticationController.login(user);
		assertEquals(HttpStatus.OK, userToken.getStatusCode());
	}

	/**
	 * Test invalid login.
	 */
	@Test
	void testInvalidLogin() {
		UserLogin user = new UserLogin("user1", "user2");
		UserDetails userDetails = new User(user.getUserName(), "user1", new ArrayList<>());

		when(userService.loadUserByUsername("user1")).thenReturn(userDetails);
		Exception exception = Assertions.assertThrows(CredentialsException.class,
				() -> authenticationController.login(user));

		assertEquals("Invalid Username or password", exception.getMessage());
	}

	/**
	 * Test valid token.
	 */
	@Test
	void testValidToken() {
		UserLogin user = new UserLogin("user1", "user1");
		UserDetails userDetails = new User(user.getUserName(), user.getPassword(), new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(true);
		when(jwtUtil.extractUsername("token")).thenReturn("user1");
		when(userService.loadUserByUsername("user1")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.getValidity("Bearer token");
		assertTrue(validity.getBody().toString().contains("true"));
	}

	/**
	 * Test invalid token.
	 */
	@Test
	void testInvalidToken() {
		UserLogin user = new UserLogin("user1", "user1");
		UserDetails userDetails = new User(user.getUserName(), user.getPassword(), new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(false);
		when(jwtUtil.extractUsername("token")).thenReturn("user1");
		when(userService.loadUserByUsername("user1")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.getValidity("Bearer token");
		assertEquals(true, validity.getBody().toString().contains("false"));
	}
	
	/**
	 * Test invalid token when signature invalid.
	 */
	@Test
	void testInvalidTokenWhenSignatureInvalid() {
		UserLogin user = new UserLogin("user1", "user1");
		UserDetails userDetails = new User(user.getUserName(), user.getPassword(), new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenThrow(SignatureException.class);
		when(jwtUtil.extractUsername("token")).thenReturn("user1");
		when(userService.loadUserByUsername("user1")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.getValidity("Bearer token");
		assertEquals(true, validity.getBody().toString().contains("false"));
	}
}
