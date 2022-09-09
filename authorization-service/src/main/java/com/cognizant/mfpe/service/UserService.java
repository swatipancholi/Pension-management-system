package com.cognizant.mfpe.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.cognizant.mfpe.model.UserLogin;
import com.cognizant.mfpe.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * The Class UserService.
 */
@Service

/** The Constant log. */
@Slf4j
public class UserService implements UserDetailsService {

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username)   {
		log.info("BEGIN   -   [loadUserByUsername()]");
		log.debug("Username : " + username);

		UserLogin userLogin = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> username or email: " + username));
		
		log.debug("UserLogin : " + userLogin);
		UserDetails user = new User(userLogin.getUserName(), userLogin.getPassword(), new ArrayList<>());
		log.debug("User : " + user);

		log.info("END   -   [loadUserByUsername()]");
		return user;
	}

	
}