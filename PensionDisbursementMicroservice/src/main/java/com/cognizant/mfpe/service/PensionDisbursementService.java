package com.cognizant.mfpe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.mfpe.exception.TokenInvalidException;
import com.cognizant.mfpe.model.PensionerDetail;
import com.cognizant.mfpe.model.ProcessPensionInput;
import com.cognizant.mfpe.restclients.AuthServiceProxy;
import com.cognizant.mfpe.restclients.PensionerDetailClient;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class PensionDisbursementService.
 */
@Service

/** The Constant log. */
@Slf4j
public class PensionDisbursementService {

	/** The pensioner detail client. */
	@Autowired
	private PensionerDetailClient pensionerDetailClient;

	/** The auth service proxy. */
	@Autowired
	private AuthServiceProxy authServiceProxy;

	/** The Constant SUCCESS. */
	private static final int SUCCESS = 10;
	
	/** The Constant ERROR. */
	private static final int ERROR = 21;
	
	/** The Constant PUBLIC. */
	private static final String PUBLIC = "PUBLIC";
	
	/** The Constant PRIVATE. */
	private static final String PRIVATE = "PRIVATE";

	/**
	 * Gets the pension disbursement.
	 *
	 * @param token the token
	 * @param processPensionInput the process pension input
	 * @return the pension disbursement
	 * @throws TokenInvalidException the token invalid exception
	 */
	public Integer getPensionDisbursement(String token, ProcessPensionInput processPensionInput)
			throws TokenInvalidException {
		log.info("BEGIN - [getPensionDisbursement()]");
		try {
			if (!authServiceProxy.validateToken(token)) {
				throw new TokenInvalidException("Token is Invalid");
			}
		} catch (FeignException e) {
			throw new TokenInvalidException("Token is Invalid or Expired");
		}

		PensionerDetail pensionDetail = null;
		pensionDetail = getPensionDetail(token, processPensionInput.getAadharNumber());
		if (pensionDetail == null)
			return ERROR;
		String bankType = pensionDetail.getBank().getBankType();
		Double bankCharge = processPensionInput.getBankCharge();

		if (validateBankCharge(bankType.toUpperCase(), bankCharge)
				&& validatePension(pensionDetail, processPensionInput.getPensionAmount())) {
			return SUCCESS;
		}
		log.info("END - [getPensionDisbursement()]");
		return ERROR;
	}

	/**
	 * Gets the pension detail.
	 *
	 * @param token the token
	 * @param aadharNumber the aadhar number
	 * @return the pension detail
	 */
	public PensionerDetail getPensionDetail(String token, String aadharNumber) {
		log.info("BEGIN - [getPensionDetail()]");
		PensionerDetail pensionerDetail = pensionerDetailClient.getPensionerDetails(token, aadharNumber);
		log.debug("Pensioner Detaik :=" + pensionerDetail);
		log.info("END - [getPensionDetail()]");
		return pensionerDetail;
	}

	/**
	 * Validate pension.
	 *
	 * @param pensionerDetail the pensioner detail
	 * @param calculatedPension the calculated pension
	 * @return true, if successful
	 */
	public boolean validatePension(PensionerDetail pensionerDetail, double calculatedPension) {
		double pensionAmount = 0;
		log.info("BEGIN - [validatePension()]");
		if (pensionerDetail.getPensionType().equalsIgnoreCase("self")) {
			pensionAmount = 0.8 * pensionerDetail.getSalary() + pensionerDetail.getAllowance();
		} else if (pensionerDetail.getPensionType().equalsIgnoreCase("family")) {
			pensionAmount = 0.5 * pensionerDetail.getSalary() + pensionerDetail.getAllowance();
		}
		log.debug("Pension Amount :=" + pensionAmount);
		log.info("END - [validatePension()]");
		return pensionAmount == calculatedPension;
	}

	/**
	 * Validate bank charge.
	 *
	 * @param bankType the bank type
	 * @param bankCharge the bank charge
	 * @return true, if successful
	 */
	public boolean validateBankCharge(String bankType, Double bankCharge) {
		log.info("BEGIN - [validateBankCharge()]");
		switch (bankType) {
		case PRIVATE:
			if (bankCharge == 550)
				return true;// Pension disbursement Success
			else if (bankCharge == 0)
				return false;// Bank service charge not paid now it should pe paid
			break;
		case PUBLIC:
			if (bankCharge == 500)
				return true;// Pension disbursement Success
			else if (bankCharge == 0)
				return false;// Bank service charge not paid
			break;
		default:
			return false;// Bank service charge less or greater than required
		}
		log.info("END - [validateBankCharge()]");
		return false;
	}
}