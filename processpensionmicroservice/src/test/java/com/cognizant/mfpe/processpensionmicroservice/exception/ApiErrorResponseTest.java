package com.cognizant.mfpe.processpensionmicroservice.exception;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


// TODO: Auto-generated Javadoc
/**
 * The Class ApiErrorResponseTest.
 */
class ApiErrorResponseTest {

	/**
	 * Test pension detail bean.
	 */
	@Test
	void testPensionDetailBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection().addFactory(LocalDateTime.class, new LocalDateTimeFactory());
		beanTester.testBean(ApiErrorResponse.class);
	}
}
