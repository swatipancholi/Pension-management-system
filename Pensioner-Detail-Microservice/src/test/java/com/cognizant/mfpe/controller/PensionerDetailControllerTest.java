package com.cognizant.mfpe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.mfpe.exception.AadharNotFoundException;
//import com.cognizant.mfpe.exception.InvalidPensionerException;
import com.cognizant.mfpe.exception.InvalidTokenException;
import com.cognizant.mfpe.model.BankDetail;
import com.cognizant.mfpe.model.PensionerDetail;
import com.cognizant.mfpe.proxy.AuthServiceProxy;
import com.cognizant.mfpe.service.PensionerDetailServiceImpl;
import com.cognizant.mfpe.util.DateUtil;


/**
 * The Class PensionerDetailControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc
class PensionerDetailControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The pensioner detail controller. */
	@InjectMocks
	private PensionerDetailController pensionerDetailController;

	/** The pensioner service. */
	@Mock
	private PensionerDetailServiceImpl pensionerService;

	/** The auth proxy. */
	@MockBean
	private AuthServiceProxy authProxy;

	/**
	 * Context loads.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void contextLoads() throws Exception {
		assertThat(pensionerDetailController).isNotNull();
	}

	/**
	 * Test pensioner detail when aadhaar found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionerDetailWhenAadhaarFound() throws Exception {
		String token = "dummy";
		when(authProxy.validateToken("Bearer " + token)).thenReturn(true);
		when(pensionerService.findAadhaar("UI123456789DAI")).thenReturn(new PensionerDetail("Gaurav Mishra",DateUtil.parseDate("1888-04-01"),"",8552, 525,"", new BankDetail()));
		this.mockMvc.perform(get("/123456789012").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("pensionerName").value("Shubham"));
	}

	/**
	 * Test pensioner detail with invalid token.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionerDetailWithInvalidToken() throws Exception {
		String token = "dummy";
		when(authProxy.validateToken("Bearer " + token)).thenReturn(false);
		when(pensionerService.findAadhaar("123456789012")).thenThrow(InvalidTokenException.class);
		this.mockMvc.perform(get("/123456789012").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)).andDo(print())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidTokenException))
				.andExpect(result -> assertEquals("Token is Invalid", result.getResolvedException().getMessage()));
	}

	/**
	 * Test pensioner detail returns exception on unknown aadhaar number.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPensionerDetailReturnsExceptionOnUnknownAadhaarNumber() throws Exception {
		String token = "dummy";
		when(authProxy.validateToken("Bearer " + token)).thenReturn(true);
		this.mockMvc.perform(get("/0123489").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)).andDo(print())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof AadharNotFoundException))
				.andExpect(result -> assertEquals("AadharNumber Not Found",
						result.getResolvedException().getMessage()));
	}

}
