package com.cognizant.mfpe.processpensionmicroservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.mfpe.processpensionmicroservice.exception.AadharNotFoundException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidDetailsEnteredException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidTokenException;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerInput;
import com.cognizant.mfpe.processpensionmicroservice.model.ProcessPensionInput;
import com.cognizant.mfpe.processpensionmicroservice.proxy.AuthServiceProxy;
import com.cognizant.mfpe.processpensionmicroservice.service.ProcessPensionServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcessPensionControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ProcessPensionControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The process pension controller. */
	@Autowired
	private ProcessPensionController processPensionController;
	
	/** The process pension service. */
	@MockBean
	private ProcessPensionServiceImpl processPensionService;
	
	/** The auth service. */
	@MockBean
	private AuthServiceProxy authService;

	/**
	 * Context loads.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void contextLoads() throws Exception {
		assertThat(processPensionController).isNotNull();
	}

	/**
	 * Test pension detail return valid details for self pension.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DirtiesContext
	void testPensionDetailReturnValidDetailsForSelfPension() throws Exception {
		String token = "dummy";
		
		PensionDetail PensionerDetailActual = new PensionDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1023", "self", 15.0);
		PensionerInput pensionerInput = new PensionerInput("Shivam",LocalDate.of(1998,8,3), "FBCJ1023", "1234567891234567", "self");
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenReturn(PensionerDetailActual);	
		PensionDetail pensionerDetailExpected=processPensionController.getPensionDetails(token, pensionerInput);
		assertEquals(pensionerDetailExpected,PensionerDetailActual);
	}
	
	/**
	 * Test pension detail return valid details for family pension.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DirtiesContext
	void testPensionDetailReturnValidDetailsForFamilyPension() throws Exception {
		String token = "dummy";
		PensionDetail PensionerDetailActual = new PensionDetail("Shivam",LocalDate.of(1998,8,3), "FBCJ1023", "family", 18.0);
		PensionerInput pensionerInput = new PensionerInput("Shivam",LocalDate.of(1998,8,3), "FBCJ1022", "1234567891234567", "family");
		
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenReturn(PensionerDetailActual);	
		
		PensionDetail pensionerDetailExpected=processPensionController.getPensionDetails(token, pensionerInput);
		assertEquals(pensionerDetailExpected,PensionerDetailActual);
	}

	/**
	 * Test pension detail returns exception on invalid name.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionDetailReturnsExceptionOnInvalidName() throws Exception {
		String token = "dummy";
		
		PensionerInput pensionerInput = new PensionerInput("Sam",LocalDate.of(1998,8,3), "FBCJ1022", "1234567891234567", "family");
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenThrow(new InvalidDetailsEnteredException("Invalid pensioner detail provided, please provide valid detail."));
		
		Assertions.assertThrows(InvalidDetailsEnteredException.class, () -> {
			processPensionController.getPensionDetails(token, pensionerInput);
		  });
	}
	
	/**
	 * Test pension detail returns exception on invalid date of birth.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionDetailReturnsExceptionOnInvalidDateOfBirth() throws Exception {
		String token = "dummy";
		
		PensionerInput pensionerInput = new PensionerInput("Shubham",LocalDate.of(1998,8,3), "PCASD1234Q", "123456789012", "self");
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenThrow(new InvalidDetailsEnteredException("Invalid pensioner detail provided, please provide valid detail."));
		
		Assertions.assertThrows(InvalidDetailsEnteredException.class, () -> {
			processPensionController.getPensionDetails(token, pensionerInput);
		  });
	}
	
	/**
	 * Test pension detail returns exception on invalid pan.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionDetailReturnsExceptionOnInvalidPan() throws Exception {
		String token = "dummy";
		
		PensionerInput pensionerInput = new PensionerInput("Shubham",LocalDate.of(1998,8,3), "PCAS234Q", "123456789012", "self");
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenThrow(new InvalidDetailsEnteredException("Invalid pensioner detail provided, please provide valid detail."));
		
		Assertions.assertThrows(InvalidDetailsEnteredException.class, () -> {
			processPensionController.getPensionDetails(token, pensionerInput);
		  });
	}
	
	/**
	 * Test pension detail returns exception on invalid pension type.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionDetailReturnsExceptionOnInvalidPensionType() throws Exception {
		String token = "dummy";
		
		PensionerInput pensionerInput = new PensionerInput("Shubham",LocalDate.of(1998,8,3), "PCASD1234Q", "123456789012", "family");
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenThrow(new InvalidDetailsEnteredException("Invalid pensioner detail provided, please provide valid detail."));
		
		Assertions.assertThrows(InvalidDetailsEnteredException.class, () -> {
			processPensionController.getPensionDetails(token, pensionerInput);
		  });
	}
	
	/**
	 * Test process pension when details are valid.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testProcessPensionWhenDetailsAreValid() throws Exception {
		String token = "dummy";
		
		ProcessPensionInput processPensionInput = new ProcessPensionInput("1234567891234567", 10000, 550);
		when(processPensionService.getResponse("Bearer" + token, processPensionInput))
			.thenReturn(10);
		
		this.mockMvc
			.perform(post("/ProcessPension")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"aadharNumber\":\"1234567891234567\",\"pensionAmount\":\"10000\"}")
			.header("Authorization", "Bearer " + token))
			.andExpect(status().isOk());
	}
	
	/**
	 * Test process pension when details are invalid.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testProcessPensionWhenDetailsAreInvalid() throws Exception {
		String token = "dummy";
		
		ProcessPensionInput processPensionInput = new ProcessPensionInput("1234567891233455", 1000, 550);
		when(processPensionService.getResponse("Bearer" + token, processPensionInput))
			.thenReturn(21);
		
		this.mockMvc
			.perform(post("/ProcessPension")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"aadharNumber\":\"1234567891233455\",\"pensionAmount\":\"1000\"}")
			.header("Authorization", "Bearer " + token))
			.andExpect(status().isOk());
	}
	
	/**
	 * Test details already exist in table.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDetailsAlreadyExistInTable() throws Exception {
		String token = "dummy";
		
		PensionerInput pensionerInput = new PensionerInput("Shubham",LocalDate.of(1998,8,3), "PCASD1234Q", "123456789012", "family");
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenThrow(DataIntegrityViolationException.class);	
		
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			processPensionController.getPensionDetails(token, pensionerInput);
		  });
	}
	
	/**
	 * Test when aadhar is invalid.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testWhenAadharIsInvalid() throws Exception {
		String token = "dummy";
		
		PensionerInput pensionerInput = new PensionerInput("Shubham",LocalDate.of(1998,8,3), "PCASD1234Q", "13456789012", "family");
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenThrow(AadharNotFoundException.class);	
		Assertions.assertThrows(AadharNotFoundException.class, () -> {
			processPensionController.getPensionDetails(token, pensionerInput);
		  });
		
	}
	
	/**
	 * Test when token is invalid for pension detail.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testWhenTokenIsInvalidForPensionDetail() throws Exception {
		String token = "dummy";
		
		PensionerInput pensionerInput = new PensionerInput("Shubham",LocalDate.of(1998,8,3), "PCASD1234Q", "123456789012", "family");
		when(authService.validateToken(token)).thenReturn(true);
		when(processPensionService.getPensionDetails(token, pensionerInput))
			.thenThrow(InvalidTokenException.class);	
		Assertions.assertThrows(InvalidTokenException.class, () -> {
			processPensionController.getPensionDetails(token, pensionerInput);
		  });
	}
}
