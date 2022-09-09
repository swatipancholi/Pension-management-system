package com.cognizant.mfpe.processpensionmicroservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.mfpe.processpensionmicroservice.exception.AadharNotFoundException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidDetailsEnteredException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidTokenException;
import com.cognizant.mfpe.processpensionmicroservice.model.BankDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerInput;
import com.cognizant.mfpe.processpensionmicroservice.model.ProcessPensionInput;
import com.cognizant.mfpe.processpensionmicroservice.proxy.AuthServiceProxy;
import com.cognizant.mfpe.processpensionmicroservice.proxy.PensionDisbursementMicroservivceProxy;
import com.cognizant.mfpe.processpensionmicroservice.proxy.PensionerDetailMicroserviceProxy;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcessPensionServiceImplTests.
 */
@SpringBootTest
class ProcessPensionServiceImplTests {

	/** The pensioner detail proxy. */
	@Mock
	private PensionerDetailMicroserviceProxy pensionerDetailProxy;
	
	/** The pension disbursement proxy. */
	@Mock
	private PensionDisbursementMicroservivceProxy pensionDisbursementProxy;
	
	/** The auth service. */
	@Mock
	private AuthServiceProxy authService;

	/** The process pension service. */
	@InjectMocks
	private ProcessPensionServiceImpl processPensionService;

	/**
	 * Test get pension details for self pension.
	 *
	 * @throws InvalidDetailsEnteredException the invalid details entered exception
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@Test
	void testGetPensionDetailsForSelfPension() throws InvalidDetailsEnteredException, InvalidTokenException, AadharNotFoundException{
		String token = "Bearer dummy";
		
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1023", 10000,
				5000, "self", new BankDetail("HDFC", "242342342", "public"));
		PensionerInput pensionerInput = new PensionerInput("Shivam", LocalDate.of(1998,8,3), "FBCJ1023",
				"1234567891234567", "self");
		when(authService.validateToken(token)).thenReturn(true);
		when(pensionerDetailProxy.fetchPensionerDetailByAadhaar(token, pensionerInput.getAadharNumber())).thenReturn(pensionerDetail);
		

		PensionDetail pensionDetail = processPensionService.getPensionDetails(token, pensionerInput);

		assertThat(pensionDetail)
			.hasFieldOrPropertyWithValue("pensionerName", "Shivam")
			.hasFieldOrPropertyWithValue("pensionerDob",LocalDate.of(1998,8,3))
			.hasFieldOrPropertyWithValue("pan", "FBCJ1023")
			.hasFieldOrPropertyWithValue("pensionType", "self")
			.hasFieldOrPropertyWithValue("pensionAmount", 13000.0);
	}
	
	/**
	 * Test get pension details for family pension.
	 *
	 * @throws InvalidDetailsEnteredException the invalid details entered exception
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@Test
	void testGetPensionDetailsForFamilyPension() throws InvalidDetailsEnteredException, InvalidTokenException, AadharNotFoundException {
		String token = "Bearer dummy";
		
		PensionerDetail pensionerDetail = new PensionerDetail("Sam",LocalDate.of(1998,8,3), "FHJF4563", 10000,
				5000, "family", new BankDetail("HDFC", "34534535", "public"));
		PensionerInput pensionerInput = new PensionerInput("Sam", LocalDate.of(1998,8,3), "FHJF4563",
				"1234567891234568", "family");
		when(authService.validateToken(token)).thenReturn(true);
		when(pensionerDetailProxy.fetchPensionerDetailByAadhaar(token, pensionerInput.getAadharNumber())).thenReturn(pensionerDetail);
		

		PensionDetail pensionDetail = processPensionService.getPensionDetails(token, pensionerInput);

		assertThat(pensionDetail)
			.hasFieldOrPropertyWithValue("pensionerName", "Sam")
			.hasFieldOrPropertyWithValue("pensionerDob", LocalDate.of(1998,8,3))
			.hasFieldOrPropertyWithValue("pan", "FHJF4563")
			.hasFieldOrPropertyWithValue("pensionType", "family")
			.hasFieldOrPropertyWithValue("pensionAmount", 10000.0);
	}
	
	/**
	 * Test get pension details for invalid name.
	 *
	 * @throws InvalidDetailsEnteredException the invalid details entered exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@Test
	void testGetPensionDetailsForInvalidName() throws InvalidDetailsEnteredException, AadharNotFoundException {
		String token = "Bearer dummy";
		
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1023", 10000,
				5000, "self", new BankDetail("HDFC", "242342342", "public"));
		PensionerInput pensionerInput = new PensionerInput("Sam", LocalDate.of(1998,8,3), "FBCJ1023",
				"1234567891234567", "self");
		when(authService.validateToken(token)).thenReturn(true);
		when(pensionerDetailProxy.fetchPensionerDetailByAadhaar(token, pensionerInput.getAadharNumber())).thenReturn(pensionerDetail);
		
		InvalidDetailsEnteredException exception = assertThrows(InvalidDetailsEnteredException.class,
				() -> processPensionService.getPensionDetails(token, pensionerInput));
		assertEquals("Invalid pensioner detail provided, please provide valid detail.", exception.getMessage());
	}
	
//	@Test
//	void testAadharIsInvalid() throws AadharNotFoundException {
//		String token = "Bearer dummy";
//		
//		PensionerInput pensionerInput = new PensionerInput("Sam",LocalDate.of(1998,8,3), "FBCJ1023",
//				"1234567891234567", "self");
//		when(authService.validateToken(token)).thenReturn(true);
//		when(pensionerDetailProxy.fetchPensionerDetailByAadhaar(token, pensionerInput.getAadharNumber())).thenThrow(AadharNotFoundException.class);
//		
//		AadharNotFoundException exception = assertThrows(AadharNotFoundException.class,
//				() -> processPensionService.getPensionDetails(token, pensionerInput));
//		assertEquals("Aadhaar Number doesn't exist in the database.", exception.getMessage());
//	}
	
	/**
 * Test token is invalid for pension detail.
 */
@Test
	void testTokenIsInvalidForPensionDetail() {
		String token = "Bearer abc";
		
		PensionerInput pensionerInput = new PensionerInput("Sam",LocalDate.of(1998,8,3), "FBCJ1023",
				"1234567891234567", "self");
		when(authService.validateToken(token)).thenReturn(false);
		
		InvalidTokenException exception = assertThrows(InvalidTokenException.class,
				() -> processPensionService.getPensionDetails(token, pensionerInput));
		assertEquals("Token is Invalid", exception.getMessage());
	}
	
	/**
	 * Test token is invalid for process detail.
	 */
	@Test
	void testTokenIsInvalidForProcessDetail() {
		String token = "Bearer abc";
		
		ProcessPensionInput processPensionInput = new ProcessPensionInput("1324324234", 10000, 500);
		when(authService.validateToken(token)).thenReturn(false);
		
		InvalidTokenException exception = assertThrows(InvalidTokenException.class,
				() -> processPensionService.getResponse(token, processPensionInput));
		assertEquals("Token is Invalid", exception.getMessage());
	}
	
	/**
	 * Test get response on public bank success.
	 *
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@Test
	void testGetResponseOnPublicBankSuccess() throws InvalidTokenException, AadharNotFoundException {
		String token = "Bearer dummy";
		
		ProcessPensionInput processPensionInput = new ProcessPensionInput("1324324234", 10000, 500);
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1023", 10000,
				5000, "self", new BankDetail("SBI", "242342342", "public"));
		when(authService.validateToken(token)).thenReturn(true);
		when(pensionerDetailProxy.fetchPensionerDetailByAadhaar(token, processPensionInput.getAadharNumber())).thenReturn(pensionerDetail);
		when(pensionDisbursementProxy.getPensionDisbursement(token, processPensionInput)).thenReturn(10);
		
		Integer response = processPensionService.getResponse(token, processPensionInput);
		assertEquals(10, response);
		
	}
	
	/**
	 * Test get response on private bank success.
	 *
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@Test
	void testGetResponseOnPrivateBankSuccess() throws InvalidTokenException, AadharNotFoundException {
		String token = "Bearer dummy";
		
		ProcessPensionInput processPensionInput = new ProcessPensionInput("1324324234", 10000, 550);
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1023", 10000,
				5000, "self", new BankDetail("HDFC", "242342342", "private"));
		when(authService.validateToken(token)).thenReturn(true);
		when(pensionerDetailProxy.fetchPensionerDetailByAadhaar(token, processPensionInput.getAadharNumber())).thenReturn(pensionerDetail);
		when(pensionDisbursementProxy.getPensionDisbursement(token, processPensionInput)).thenReturn(10);
		
		Integer response = processPensionService.getResponse(token, processPensionInput);
		assertEquals(10, response);
		
	}
	
	/**
	 * Test get response on failure.
	 *
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@Test
	void testGetResponseOnFailure() throws InvalidTokenException, AadharNotFoundException {
		String token = "Bearer dummy";
		
		ProcessPensionInput processPensionInput = new ProcessPensionInput("1324324234", 10000, 500);
		PensionerDetail pensionerDetail = new PensionerDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1023", 12000,
				5000, "self", new BankDetail("HDFC", "242342342", "public"));
		when(authService.validateToken(token)).thenReturn(true);
		when(pensionerDetailProxy.fetchPensionerDetailByAadhaar(token, processPensionInput.getAadharNumber())).thenReturn(pensionerDetail);
		when(pensionDisbursementProxy.getPensionDisbursement(token, processPensionInput)).thenReturn(21);
		
		Integer response = processPensionService.getResponse(token, processPensionInput);
		assertEquals(21, response);
		
	}

}
