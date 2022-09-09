package com.cognizant.mfpe.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.mockito.Mock;

import nl.jqno.equalsverifier.EqualsVerifier;


/**
 * The Class ModelTest.
 */
class ModelTest {

	/** The my user. */
	@Mock
	UserLogin myUser;

	/**
	 * Test user login bean.
	 */
	@Test
	void testUserLoginBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(UserLogin.class);
	}


	/**
	 * Test user token bean.
	 */
	@Test
	void testUserTokenBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(UserToken.class);
	}

	/**
	 * Test user token equals and hash code 2.
	 */
	@Test
	void testUserTokenEqualsAndHashCode2() {
		EqualsVerifier.simple().forClass(UserToken.class).verify();
	}

	/**
	 * Test user login all args.
	 */
	@Test
	void testUserLoginAllArgs() {
		UserLogin userLogin = new UserLogin("pavithran", "dummy");
		assertEquals("pavithran", userLogin.getUserName());
		assertEquals("dummy", userLogin.getPassword());
	}

	/**
	 * Test user token all args.
	 */
	@Test
	void testUserTokenAllArgs() {
		UserToken userToken = new UserToken("gaurav", "token");
		assertEquals("gaurav", userToken.getUserName());
		assertEquals("token", userToken.getAuthToken());
	}
}