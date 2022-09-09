package com.cognizant.mfpe.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


/**
 * The Class BankDetailTest.
 */
class BankDetailTest {

	/**
	 * Test banke detail bean.
	 */
	@Test
	void testBankeDetailBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(BankDetail.class);
	}

}
