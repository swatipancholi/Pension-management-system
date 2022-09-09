package com.cognizant.mfpe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


/**
 * The Class ProcessPensionInputTest.
 */
class ProcessPensionInputTest {

	/**
	 * Test process pension input bean.
	 */
	@Test
	void testProcessPensionInputBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ProcessPensionInput.class);
	}

	/**
	 * Test bank detail bean.
	 */
	@Test
	void testBankDetailBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(BankDetail.class);
	}

	/**
	 * Test process pensioner response.
	 */
	@Test
	void testProcessPensionerResponse() {
		ProcessPensionResponse ppR = new ProcessPensionResponse(10);
		assertEquals(10, ppR.getProcessPensionStatusCode());
	}

	/**
	 * Test pensioner detail bean.
	 */
	@Test
	void testPensionerDetailBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection().addFactory(LocalDate.class, new LocalDateTimeFactory());
		beanTester.testBean(PensionerDetail.class);
	}

	/**
	 * Test process pension response bean.
	 */
	@Test
	void testProcessPensionResponseBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ProcessPensionResponse.class);
	}

	/**
	 * Test pensioner bean.
	 */
	@Test
	void testPensionerBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ProcessPensionInput.class);
	}

	/**
	 * Test pensioner detail all args constructor.
	 */
	@Test
	void testPensionerDetailAllArgsConstructor() {
		BankDetail bankDetail = new BankDetail("ICICI", "ICIC012345", "Private");
		Date date=new Date();
		PensionerDetail pensionerDetail = new PensionerDetail("Gaurav", date, "ABX123456",
				30000, 50000, "Self", bankDetail);
		assertEquals("Gaurav", pensionerDetail.getPensionerName());
	}

}
