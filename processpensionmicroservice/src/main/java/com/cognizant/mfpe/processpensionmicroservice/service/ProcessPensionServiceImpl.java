package com.cognizant.mfpe.processpensionmicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cognizant.mfpe.processpensionmicroservice.exception.AadharNotFoundException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidDetailsEnteredException;
import com.cognizant.mfpe.processpensionmicroservice.exception.InvalidTokenException;
import com.cognizant.mfpe.processpensionmicroservice.model.Bank;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerInput;
import com.cognizant.mfpe.processpensionmicroservice.model.ProcessPensionInput;
import com.cognizant.mfpe.processpensionmicroservice.proxy.AuthServiceProxy;
import com.cognizant.mfpe.processpensionmicroservice.proxy.PensionDisbursementMicroservivceProxy;
import com.cognizant.mfpe.processpensionmicroservice.proxy.PensionerDetailMicroserviceProxy;
import com.cognizant.mfpe.processpensionmicroservice.util.ValidatePensionerDetails;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class ProcessPensionServiceImpl.
 */
@Service

/** The Constant log. */

/** The Constant log. */

/** The Constant log. */
@Slf4j
public class ProcessPensionServiceImpl implements ProcessPensionService {

	/** The pensioner detail proxy. */
	@Autowired
	private PensionerDetailMicroserviceProxy pensionerDetailProxy;

	/** The pension disbursement proxy. */
	@Autowired
	private PensionDisbursementMicroservivceProxy pensionDisbursementProxy;

	/** The auth service proxy. */
	@Autowired
	private AuthServiceProxy authServiceProxy;
	
	/** The private bank charge. */
	@Value("${privatebank.charge}")
	private double privateBankCharge;

	/** The public bank charge. */
	@Value("${publicbank.charge}")
	private double publicBankCharge;
	
	/** The private bank. */
	private static String privateBank = "private";
	
	/** The public bank. */
	private static String publicBank = "public";
	
	/** The bank list. */
	private static List<Bank> bankList;

	static {
		bankList = List.of(new Bank("HDFC", privateBank), new Bank("ICICI", privateBank), new Bank("Axis", privateBank),
				new Bank("SBI", publicBank), new Bank("PNB", publicBank), new Bank("Central Bank", publicBank),
				new Bank("Indian Bank", publicBank), new Bank("Oriental Bank of Commerce", publicBank),
				new Bank("Bandhan Bank", privateBank), new Bank("Co-Operative Bank", publicBank),
				new Bank("Allahabad", publicBank), new Bank("BOB", publicBank), new Bank("Yes", privateBank));
	}

	/**
	 * Gets the pension details.
	 *
	 * @param token the token
	 * @param pensionerInput the pensioner input
	 * @return the pension details
	 * @throws InvalidDetailsEnteredException the invalid details entered exception
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@Override
	public PensionDetail getPensionDetails(String token, PensionerInput pensionerInput)
			throws InvalidDetailsEnteredException, InvalidTokenException, AadharNotFoundException {
		log.info("BEGIN   -   [getPensionDetails()]-service");

		try {
			if (!authServiceProxy.validateToken(token)) {
				throw new InvalidTokenException("Token is Invalid");
			}
		} catch (FeignException e) {
			throw new InvalidTokenException("Token is Invalid or Expired");
		}

		log.debug("Pensioner Input : " + pensionerInput);

		double pensionAmount = 0;
		PensionerDetail pensionerDetail;
		try {
			String adharNum=pensionerInput.getAadharNumber();
			pensionerDetail = pensionerDetailProxy.fetchPensionerDetailByAadhaar(token,adharNum);
			log.info(" "+pensionerDetail);
		} catch (FeignException ex) {
			
			log.info("Indside feignException");
			throw new AadharNotFoundException("Aadhaar Number doesn't exist in the database.");
		}
		log.debug("Pensioner Details : " + pensionerDetail);

		if (!ValidatePensionerDetails.isValidDetails(pensionerInput, pensionerDetail)) {
			throw new InvalidDetailsEnteredException("Invalid pensioner detail provided, please provide valid detail.");
		}

		if (pensionerDetail.getPensionType().equalsIgnoreCase("self")) {
			pensionAmount = 0.8 * pensionerDetail.getSalary() + pensionerDetail.getAllowance();
		} else if (pensionerDetail.getPensionType().equalsIgnoreCase("family")) {
			pensionAmount = 0.5 * pensionerDetail.getSalary() + pensionerDetail.getAllowance();
		}

		log.debug("Pension Calculated : " + pensionAmount);
		log.info("END   -   [getPensionDetails()]");

		return new PensionDetail(pensionerDetail.getPensionerName(), pensionerDetail.getPensionerDob(),
				pensionerDetail.getPan(), pensionerDetail.getPensionType(), pensionAmount);
	}

	/**
	 * Gets the response.
	 *
	 * @param token the token
	 * @param processPensionInput the process pension input
	 * @return the response
	 * @throws InvalidTokenException the invalid token exception
	 * @throws AadharNotFoundException the aadhar not found exception
	 */
	@Override
	public Integer getResponse(String token, ProcessPensionInput processPensionInput) throws InvalidTokenException, AadharNotFoundException {
		log.info("BEGIN   -   [getResponse()]-service");

		try {
			if (!authServiceProxy.validateToken(token)) {
				throw new InvalidTokenException("Token is Invalid");
			}
		} catch (FeignException e) {
			throw new InvalidTokenException("Token is Invalid or Expired");
		}

		log.debug("Process Pension Input : " + processPensionInput);

		log.info(" "+processPensionInput);
		PensionerDetail pensionerDetail = pensionerDetailProxy.fetchPensionerDetailByAadhaar(token,
				processPensionInput.getAadharNumber());
		log.info(" "+pensionerDetail);
		if(pensionerDetail==null)
		{
			log.info("Pensioner detail is null");
			throw new AadharNotFoundException("Invalid Aadhar number");
		}
		
		log.debug("Pensioner Details : " + pensionerDetail);

		Optional<Bank> userBank = bankList.stream()
				.filter(bank -> bank.getName().equalsIgnoreCase(pensionerDetail.getBank().getBankName()))
				.findFirst();
		log.debug("Bank Details : " + userBank);

		
		if (userBank.isPresent() && userBank.get().getBankType().equalsIgnoreCase(publicBank)) {
			processPensionInput.setBankCharge(publicBankCharge);
		} else if (userBank.isPresent() && userBank.get().getBankType().equalsIgnoreCase(privateBank)) {
			processPensionInput.setBankCharge(privateBankCharge);
		}

		int tryForResponseCode = 3;
		int responseCode = 21;
		while (tryForResponseCode-- > 0) {
			log.info("responsecode");
			log.debug(""+tryForResponseCode);
			responseCode = pensionDisbursementProxy.getPensionDisbursement(token, processPensionInput);
			if (responseCode == 10) {
				log.info("10");
				break;
			}
		}

		log.debug("Response Code Retuned : " + responseCode);

		log.info("END   -   [getResponse()]");
		return responseCode;
	}

}
