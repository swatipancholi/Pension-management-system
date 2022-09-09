package com.cognizant.mfpe.util;

import java.text.ParseException;
import java.time.LocalDate;




/**
 * The Class DateUtil.
 */
public class DateUtil {

	/**
	 * Parses the date.
	 *
	 * @param date the date
	 * @return the local date
	 * @throws ParseException the parse exception
	 */
	public static LocalDate parseDate(String date) throws ParseException {
		LocalDate dateOutput = LocalDate.parse(date);
		return dateOutput;
	}

	
}
