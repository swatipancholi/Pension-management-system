package com.cognizant.mfpe.processpensionmicroservice.service;

import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;


/**
 * The Interface PensionDetailService.
 */
public interface PensionDetailService {
	
	/**
	 * Save.
	 *
	 * @param pensionDetail the pension detail
	 * @return the pension detail
	 */
	public PensionDetail save(PensionDetail pensionDetail);

}
