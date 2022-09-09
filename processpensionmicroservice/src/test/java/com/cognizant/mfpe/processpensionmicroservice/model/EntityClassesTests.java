package com.cognizant.mfpe.processpensionmicroservice.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.cognizant.mfpe.processpensionmicroservice.exception.LocalDateTimeFactory;

import nl.jqno.equalsverifier.EqualsVerifier;

// TODO: Auto-generated Javadoc
/**
 * The Class EntityClassesTests.
 */
class EntityClassesTests {

//	@Test
//	void testPensionDetailBean() {
//		final BeanTester beanTester = new BeanTester();
//		beanTester.getFactoryCollection().addFactory(LocalDate.class, new LocalDateTimeFactory());
//		beanTester.testBean(PensionDetail.class);
//	}

	/**
 * Test process pension input bean.
 */
@Test
	void testProcessPensionInputBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection().addFactory(LocalDate.class, new LocalDateTimeFactory());
		beanTester.testBean(ProcessPensionInput.class);
	}

	/**
	 * Test bank detail bean.
	 */
	@Test
	void testBankDetailBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection().addFactory(LocalDate.class, new LocalDateTimeFactory());
		beanTester.testBean(BankDetail.class);
	}

	/**
	 * Test bank equals and hash code.
	 */
	@Test 
	void testBankEqualsAndHashCode() {
		EqualsVerifier.simple().forClass(PensionerInput.class).verify();
	}

	/**
	 * Test bank bean.
	 */
	@Test
	void testBankBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection().addFactory(LocalDate.class, new LocalDateTimeFactory());
		beanTester.testBean(Bank.class);
	}
}
