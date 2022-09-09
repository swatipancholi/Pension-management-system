package com.cognizant.mfpe;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



/**
 * The Class PensionerDetailMicroserviceApplicationTests.
 */
@SpringBootTest(classes=PensionerDetailMicroserviceApplicationTests.class)
class PensionerDetailMicroserviceApplicationTests {

	/**
	 * Application starts.
	 */
	void applicationStarts() {
		PensionerDetailMicroserviceApplication.main(new String[] {});
		assertTrue(true);
	}

	/**
	 * Context loads.
	 */
	@Test
	void contextLoads() {
	}
}


