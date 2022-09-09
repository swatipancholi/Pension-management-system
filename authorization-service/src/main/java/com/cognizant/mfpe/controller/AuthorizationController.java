package com.cognizant.mfpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.mfpe.exception.CredentialsException;
import com.cognizant.mfpe.model.UserLogin;
import com.cognizant.mfpe.model.UserToken;
import com.cognizant.mfpe.service.JwtUtil;
import com.cognizant.mfpe.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class AuthorizationController.
 */
@RestController

/** The Constant log. */
@Slf4j
@CrossOrigin
@Api(value = "Endpoints for Authentication Service")
public class AuthorizationController {
	
	/** The jwt util. */
	@Autowired
	private JwtUtil jwtUtil;

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Login.
	 *
	 * @param userLoginCredentials the user login credentials
	 * @return the response entity
	 * @throws CredentialsException the credentials exception
	 */
	@PostMapping("/login")
	@ApiOperation(value = "userLogin", notes = "tokes user credentials and generate a JWT", httpMethod = "POST", response = ResponseEntity.class)
	public ResponseEntity<Object> login(
			 @RequestBody UserLogin userLoginCredentials)
			throws CredentialsException {
		log.info("BEGIN   -   [login(userLoginCredentials)]");
		final UserDetails userdetails = userService.loadUserByUsername(userLoginCredentials.getUserName());
		log.debug("{}", userdetails);
		if (userdetails.getPassword().equals(userLoginCredentials.getPassword())) {
			log.info("END  -   [login(userLoginCredentials)]");
			return new ResponseEntity<>(
					new UserToken(userLoginCredentials.getUserName(), jwtUtil.generateToken(userdetails)),
					HttpStatus.OK);
		} else {
			log.info("END  -   [login(userLoginCredentials)]");
			throw new CredentialsException("Invalid Username or password");
		}

	}

	/**
	 * Gets the validity.
	 *
	 * @param token1 the token 1
	 * @return the validity
	 */
	@GetMapping("/validate")
	@ApiOperation(value = "tokenValidation", notes = "returns boolean after validating JWT", httpMethod = "GET", response = ResponseEntity.class)
	public ResponseEntity<Object> getValidity(
			 @RequestHeader(name = "Authorization") String token1) {
		log.info("BEGIN   -   [getValidty(token)]");
		String token = token1.substring(7);

		try {
			UserDetails user = userService.loadUserByUsername(jwtUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtUtil.validateToken(token, user))) {
				log.debug("Valid token");
				log.info("{END} : validate()");
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				log.debug("Invalid token");
				log.info("{END} : getValidity()");
				return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			log.debug("Invalid token");
			log.info("{END} : getValidity()");
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
	}



}
