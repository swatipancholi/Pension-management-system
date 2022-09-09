package com.cognizant.mfpe.processpensionmicroservice.model;

import java.time.LocalDate;

import org.meanbean.lang.Factory;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating LocalDateTime objects.
 */
public class LocalDateTimeFactory implements Factory<LocalDate> {

	/**
	 * Creates the.
	 *
	 * @return the local date
	 */
	@Override
	public LocalDate create() {
		return LocalDate.now();
	}

}
