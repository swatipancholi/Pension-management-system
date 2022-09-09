package com.cognizant.mfpe.processpensionmicroservice.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;
import com.cognizant.mfpe.processpensionmicroservice.repository.PensionDetailRepository;


// TODO: Auto-generated Javadoc
/**
 * The Class PensionDetailsServiceImplTests.
 */
@SpringBootTest
class PensionDetailsServiceImplTests {

	/** The pension details service impl. */
	@InjectMocks
	private PensionDetailsServiceImpl pensionDetailsServiceImpl;

	/** The pension detail repository. */
	@Mock
	private PensionDetailRepository pensionDetailRepository;

	/**
	 * Test create pension detail.
	 */
	@Test
	@DirtiesContext
	void testCreatePensionDetail() {
		PensionDetail pensionDetail = new PensionDetail("Shivam", LocalDate.of(1998,8,3), "FBJP1234", "self", 10000);
		pensionDetailsServiceImpl.save(pensionDetail);
		verify(pensionDetailRepository, times(1)).save(pensionDetail);
	}

}
