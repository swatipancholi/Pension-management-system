package com.cognizant.mfpe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import com.cognizant.mfpe.util.DateUtil;


/**
 * The Class PensionerDetailTest.
 */
class PensionerDetailTest {

	/**
	 * Test pensioner detail all args constructor.
	 *
	 * @throws ParseException the parse exception
	 */
	@Test
	void testPensionerDetailAllArgsConstructor() throws ParseException {
		BankDetail bankDetail = new BankDetail("ICICI", 012345, "Private");
		PensionerDetail pensionerDetail = new PensionerDetail("Gaurav",DateUtil.parseDate("1888-04-01"), "ABX123456",
				30000, 50000, "Self", bankDetail);
		assertEquals("Gaurav", pensionerDetail.getPensionerName());
		
		PensionerDetail pensionerDetail2=new PensionerDetail();
		BankDetail bankDetail2=new BankDetail();
		bankDetail2.setAccountNumber(012345);
		bankDetail2.setBankName("ICICI");
		bankDetail2.setBankType("Private");
		pensionerDetail2.setPensionerName("Gaurav");
		pensionerDetail2.setPensionerDob(DateUtil.parseDate("1888-04-01"));
		pensionerDetail2.setPan("ABX123456");
		pensionerDetail2.setSalary(30000);
		pensionerDetail2.setAllowance(50000);
		pensionerDetail2.setPensionType("Self");
		pensionerDetail2.setBank(bankDetail2);
		assertEquals("Gaurav", pensionerDetail2.getPensionerName());
		
	}

}
