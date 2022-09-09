package com.cognizant.mfpe.processpensionmicroservice.exception;

import java.time.LocalDateTime;

import org.meanbean.lang.Factory;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating LocalDateTime objects.
 */
public class LocalDateTimeFactory implements Factory<LocalDateTime> {

	/**
	 * Creates the.
	 *
	 * @return the local date time
	 */
	@Override
	public LocalDateTime create() {
		return LocalDateTime.now();
	}

}
