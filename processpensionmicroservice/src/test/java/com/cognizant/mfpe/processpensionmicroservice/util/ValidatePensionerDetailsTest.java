package com.cognizant.mfpe.processpensionmicroservice.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.cognizant.mfpe.processpensionmicroservice.model.BankDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerInput;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidatePensionerDetailsTest.
 */
class ValidatePensionerDetailsTest {

	/**
	 * Test valid details.
	 */
	@Test
	void testValidDetails() {
		PensionerInput pensionerInput = new PensionerInput("Shivam", LocalDate.of(1998,8,3), "FBCJ1022", "1234567891234567",
				"self");
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1022",
				100000, 20000, "self", new BankDetail());
		assertTrue(ValidatePensionerDetails.isValidDetails(pensionerInput, pensionerDetail));
	}

	/**
	 * Test invalid valid name.
	 */
	@Test
	void testInvalidValidName() {
		PensionerInput pensionerInput = new PensionerInput("Shivam", LocalDate.of(1998,8,3), "FBCJ1022", "1234567891234567",
				"self");
		PensionerDetail pensionerDetail = new PensionerDetail("Sam",LocalDate.of(1998,8,3), "FBCJ1022", 100000,
				20000, "self", new BankDetail());
		assertFalse(ValidatePensionerDetails.isValidDetails(pensionerInput, pensionerDetail));
	}

	/**
	 * Test invalid valid date of birth.
	 */
	@Test
	void testInvalidValidDateOfBirth() {
		PensionerInput pensionerInput = new PensionerInput("Shivam",LocalDate.of(1998,8,3), "FBCJ1022", "1234567891234567",
				"self");
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam", LocalDate.of(1998,9,3),
				"FBCJ1022", 100000, 20000, "self", new BankDetail());
		assertFalse(ValidatePensionerDetails.isValidDetails(pensionerInput, pensionerDetail));
	}

	/**
	 * Test invalid pan.
	 */
	@Test
	void testInvalidPan() {
		PensionerInput pensionerInput = new PensionerInput("Shivam",LocalDate.of(1998,8,3), "FBCJ1022", "1234567891234567",
				"self");
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam",LocalDate.of(1998,8,3), "FBZJ1022",
				100000, 20000, "self", new BankDetail());
		assertFalse(ValidatePensionerDetails.isValidDetails(pensionerInput, pensionerDetail));
	}

	/**
	 * Test invalid pension type.
	 */
	@Test
	void testInvalidPensionType() {
		PensionerInput pensionerInput = new PensionerInput("Shivam", LocalDate.of(1998,8,3), "FBCJ1022", "1234567891234567",
				"self");
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1022",
				100000, 20000, "family", new BankDetail());
		assertFalse(ValidatePensionerDetails.isValidDetails(pensionerInput, pensionerDetail));
	}
}
