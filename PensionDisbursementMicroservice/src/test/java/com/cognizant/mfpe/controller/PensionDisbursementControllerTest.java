package com.cognizant.mfpe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.mfpe.controller.PensionDisbursementController;
import com.cognizant.mfpe.exception.TokenInvalidException;
import com.cognizant.mfpe.model.ProcessPensionInput;
import com.cognizant.mfpe.restclients.AuthServiceProxy;
import com.cognizant.mfpe.service.PensionDisbursementService;

//import com.cts.project.exception.TokenInvalidException;
//import com.cts.project.model.ProcessPensionInput;
//import com.cts.project.restclients.AuthServiceProxy;
//import com.cts.project.service.PensionDisbursementService;

import feign.FeignException;


/**
 * The Class PensionDisbursementControllerTest.
 */
@AutoConfigureMockMvc
@SpringBootTest
class PensionDisbursementControllerTest {

	/** The pension disbursement controller. */
	@InjectMocks
	private PensionDisbursementController pensionDisbursementController;
	
	/** The pension disbursement service. */
	@Mock
	private PensionDisbursementService pensionDisbursementService;

	/** The auth service proxy. */
	@MockBean
	private AuthServiceProxy authServiceProxy;
	
	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test pensioner detail invalid exception.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionerDetailInvalidException() throws Exception {
		String token = "dummy";
		when(authServiceProxy.validateToken("Bearer " + token)).thenReturn(false);
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 15600.0, 550.0);
		when(pensionDisbursementService.getPensionDisbursement(token, ppi)).thenThrow(TokenInvalidException.class);

		this.mockMvc.perform(post("/disbursepension/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"aadharNumber\":\"UI123456789DAI\",\"pensionAmount\":\"29103\",\"bankCharge\":\"550\"}"))
				.andExpect(status().isUnauthorized())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof TokenInvalidException))
				.andExpect(result -> assertEquals("Token is Invalid", result.getResolvedException().getMessage()));

	}

	/**
	 * Test pensioner detail feign exception.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionerDetailFeignException() throws Exception {
		String token = "dummy";
		when(authServiceProxy.validateToken("Bearer " + token)).thenReturn(true);
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 15600.0, 550.0);
		when(pensionDisbursementService.getPensionDisbursement(token, ppi)).thenThrow(FeignException.class);
		this.mockMvc.perform(post("/disbursepension/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"aadharNumber\":\"UI123456789DAI\",\"pensionAmount\":\"29103\",\"bankCharge\":\"550\"}"))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof FeignException));
	}

	/**
	 * Test get pension disbursement success.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementSuccess() throws TokenInvalidException {
		String token = "dummyToken";
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 15600.0, 550.0);
		Mockito.when(pensionDisbursementService.getPensionDisbursement(token, ppi)).thenReturn(10);
		assertEquals(10, pensionDisbursementController.getPensionDisbursement(token, ppi));
	}

	/**
	 * Test get pension disbursement service charge not paid.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementServiceChargeNotPaid() throws TokenInvalidException, TokenInvalidException {
		String token = "dummyToken";
		ProcessPensionInput ppi2 = new ProcessPensionInput("546789641236", 15600.0, 0.0);
		Mockito.when(pensionDisbursementService.getPensionDisbursement(token, ppi2)).thenReturn(20);
		assertEquals(20, pensionDisbursementController.getPensionDisbursement(token, ppi2));
	}

	/**
	 * Test get pension disbursement unkown error.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void testGetPensionDisbursementUnkownError() throws TokenInvalidException {
		String token = "dummyToken";
		ProcessPensionInput ppi3 = new ProcessPensionInput("546789641236", 15600.0, 500.0);
		Mockito.when(pensionDisbursementService.getPensionDisbursement(token, ppi3)).thenReturn(21);
		assertEquals(21, pensionDisbursementController.getPensionDisbursement(token, ppi3));
	}

	/**
	 * Invalid exception.
	 *
	 * @throws TokenInvalidException the token invalid exception
	 */
	@Test
	void InvalidException() throws TokenInvalidException {
		String token = "dummyToken";
		Mockito.when(authServiceProxy.validateToken("Bearer " + token)).thenReturn(false);
		ProcessPensionInput ppi = new ProcessPensionInput("546789641236", 15600.0, 550.0);
		Mockito.when(pensionDisbursementService.getPensionDisbursement(token, ppi)).thenReturn(10);
		assertEquals(10, pensionDisbursementController.getPensionDisbursement(token, ppi));
	}

}