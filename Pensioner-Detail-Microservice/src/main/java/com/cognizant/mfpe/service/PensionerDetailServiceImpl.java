package com.cognizant.mfpe.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cognizant.mfpe.exception.AadharNotFoundException;
import com.cognizant.mfpe.exception.InvalidTokenException;
import com.cognizant.mfpe.model.BankDetail;
import com.cognizant.mfpe.model.PensionerDetail;
import com.cognizant.mfpe.util.DateUtil;


/**
 * The Class PensionerDetailServiceImpl.
 */
@Service
public class PensionerDetailServiceImpl implements PensionerDetailService {

	

	/**
	 * Find aadhaar.
	 *
	 * @param aadhaarNumberStr the aadhaar number str
	 * @return the pensioner detail
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AadharNotFoundException the aadhar not found exception
	 * @throws NumberFormatException the number format exception
	 * @throws ParseException the parse exception
	 * @throws InvalidTokenException the invalid token exception
	 */
	public PensionerDetail findAadhaar(String aadhaarNumberStr)
			throws IOException, AadharNotFoundException, ParseException, InvalidTokenException {
		
		/** The pension details. */
		Map<Long, PensionerDetail> pensionDetails;
		String line = "";
		long aadhaarNumber=Long.parseLong(aadhaarNumberStr);
		pensionDetails = new HashMap<>();
		PensionerDetail pensionerDetailOutput;
		BufferedReader br = new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream("/details.csv")));
		
		while ((line = br.readLine()) != null)
		{
		
			String[] person = line.split(",");
			
			PensionerDetail pd = new PensionerDetail(person[1], DateUtil.parseDate(person[2]), person[3],
					Double.parseDouble(person[4]), Double.parseDouble(person[5]), person[6],
					new BankDetail(person[7], Long.parseLong(person[8]), person[9]));
			pensionDetails.put(Long.parseLong(person[0]), pd);
		}

		
		if (pensionDetails.containsKey(aadhaarNumber)) {
			
			pensionerDetailOutput= pensionDetails.get(aadhaarNumber);
			return pensionerDetailOutput;
			
		} else {
			
			throw new AadharNotFoundException("AadharNumber Not Found");
		}
	}

}
