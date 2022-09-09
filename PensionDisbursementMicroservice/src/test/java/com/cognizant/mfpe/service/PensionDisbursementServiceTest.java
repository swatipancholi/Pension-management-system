package com.cognizant.mfpe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.mfpe.exception.TokenInvalidException;
import com.cognizant.mfpe.model.BankDetail;
import com.cognizant.mfpe.model.PensionerDetail;
import com.cognizant.mfpe.model.ProcessPensionInput;
import com.cognizant.mfpe.restclients.AuthServiceProxy;
import com.cognizant.mfpe.restclients.PensionerDetailClient;
import com.cognizant.mfpe.service.PensionDisbursementService;


/**
 * The Class PensionDisbursementServiceTest.
 */
@SpringBootTest
class PensionDisbursementServiceTest {

	/** The pension disbursement service. */
	@InjectMocks
	private PensionDisbursementService pensionDisbursementService;

	/** The pensioner detail client. */
	@Mock
	private PensionerDetailClient pensionerDetailClient;

	/** The auth service proxy. */
	@Mock
	private AuthServiceProxy authServiceProxy;

	/**
	 * Test get pension detail.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDetail() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Self",
				new BankDetail("Punjab National Bank", "9999689745", "Public"));
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, "546789641236")).thenReturn(pensionerDetail);
		assertEquals(pensionDisbursementService.getPensionDetail(token, "546789641236"), pensionerDetail);
	}

	/**
	 * Test get pension disbursement unknown error.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementUnknownError() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Self",
				new BankDetail("Punjab National Bank", "9999689745", "Public"));
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 15600.0, 900.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(21, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement success.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementSuccess() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Self",
				new BankDetail("Punjab National Bank", "9999689745", "Public"));
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 15600.0, 500.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(10, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement private bank fail.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementPrivateBankFail() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Family",
				new BankDetail("Punjab National Bank", "9999689745", "Private"));
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 9200.0, 550.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(21, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement private bank success.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementPrivateBankSuccess() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 16000.0, 1200.0, "Family",
				new BankDetail("Punjab National Bank", "9999689745", "Private"));
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 9200.0, 550.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(10, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement private bank unknown error.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementPrivateBankUnknownError() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Family",
				new BankDetail("Punjab National Bank", "9999689745", "Private"));
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 9200.0, 5250.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(21, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement unknown error wrong bank type.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementUnknownErrorWrongBankType() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Family",
				new BankDetail("Punjab National Bank", "9999689745", "Wrongbank"));
		;
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 9200.0, 5250.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(21, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement wrong aadhar.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementWrongAadhar() throws TokenInvalidException {
		String token = "dummyToken";
		PensionerDetail pensionerDetail = null;
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 15600.0, 900.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(21, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement service charge not paid.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementServiceChargeNotPaid() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Self",
				new BankDetail("Punjab National Bank", "9999689745", "Public"));

		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 15600.0, 0.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(21, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement private bank charge not paid.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementPrivateBankChargeNotPaid() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Family",
				new BankDetail("Punjab National Bank", "9999689745", "Private"));
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 9200.0, 0.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(21, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement wrong pension type.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementWrongPensionType() throws TokenInvalidException {
		String token = "dummyToken";
		Date date =new Date(1974, 8, 15);
		PensionerDetail pensionerDetail = new PensionerDetail("Rashmi Ranjan",
				date, "AAA12569CC", 18000.0, 1200.0, "Failmy",
				new BankDetail("Punjab National Bank", "9999689745", "Public"));
		;
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 9200.0, 5250.0);
		when(authServiceProxy.validateToken(token)).thenReturn(true);
		when(pensionerDetailClient.getPensionerDetails(token, ppi.getAadharNumber())).thenReturn(pensionerDetail);
		assertEquals(21, pensionDisbursementService.getPensionDisbursement(token, ppi));
	}

}
