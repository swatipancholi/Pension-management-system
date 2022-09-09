package com.cognizant.mfpe.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.mfpe.model.UserLogin;
import com.cognizant.mfpe.repository.UserRepository;


/**
 * The Class UserServiceTest.
 */
@SpringBootTest(classes = UserService.class)
class UserServiceTest {

	/** The user repo. */
	@MockBean
	private UserRepository userRepo;

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Testload user by username.
	 */
	@Test
	void testloadUserByUsername() {
		UserLogin user = new UserLogin("admin", "admin");
		when(userRepo.findById("admin")).thenReturn(Optional.of(user));
		UserDetails userDetails = new User("admin", "admin", new ArrayList<>());
		assertEquals(userDetails, userService.loadUserByUsername("admin"));
	}

}