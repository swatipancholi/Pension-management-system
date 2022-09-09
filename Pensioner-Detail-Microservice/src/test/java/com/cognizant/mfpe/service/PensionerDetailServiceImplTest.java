package com.cognizant.mfpe.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.mfpe.exception.AadharNotFoundException;
import com.cognizant.mfpe.exception.InvalidTokenException;
import com.cognizant.mfpe.model.BankDetail;
import com.cognizant.mfpe.model.PensionerDetail;
import com.cognizant.mfpe.util.DateUtil;


/**
 * The Class PensionerDetailServiceImplTest.
 */
@SpringBootTest(classes = PensionerDetailServiceImplTest.class)
@AutoConfigureMockMvc
public class PensionerDetailServiceImplTest {

	/** The pensioner detail service. */
	@InjectMocks
	private PensionerDetailServiceImpl pensionerDetailService;
	
	/**
	 * Test not null pension detail service object.
	 */
	@Test
	public void testNotNullPensionDetailServiceObject() {
		assertNotNull(pensionerDetailService);
	}

	/**
	 * Test correct details returned from service with correct aadhar number.
	 *
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AadharNotFoundException the aadhar not found exception
	 * @throws ParseException the parse exception
	 * @throws InvalidTokenException the invalid token exception
	 */
	@Test
	public void testCorrectDetailsReturnedFromServiceWithCorrectAadharNumber() throws NumberFormatException, IOException, AadharNotFoundException, ParseException, InvalidTokenException  {

			PensionerDetail pensionerDetailExpected = new PensionerDetail("Shubham",DateUtil.parseDate("1888-04-01"), "PCASD1234Q",
					27000, 10000, "self", new BankDetail("ICICI", 12345678, "private"));
			PensionerDetail pensionerDetailActual=pensionerDetailService.findAadhaar("123456789012");
			assertNotNull(pensionerDetailActual);
			assertEquals(pensionerDetailExpected.getPensionerName(),pensionerDetailActual.getPensionerName());
			
	}

}