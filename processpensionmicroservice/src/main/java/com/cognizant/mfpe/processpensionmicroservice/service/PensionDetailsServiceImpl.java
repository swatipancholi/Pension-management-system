package com.cognizant.mfpe.processpensionmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;
import com.cognizant.mfpe.processpensionmicroservice.repository.PensionDetailRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * The Class PensionDetailsServiceImpl.
 */
@Service

/** The Constant log. */

/** The Constant log. */
@Slf4j
public class PensionDetailsServiceImpl implements PensionDetailService {
	
	/** The pension detail repository. */
	@Autowired
	private PensionDetailRepository pensionDetailRepository;

	/**
	 * Save.
	 *
	 * @param pensionDetail the pension detail
	 * @return the pension detail
	 */
	@Override
	public PensionDetail save(PensionDetail pensionDetail) {
		
		log.info("BEGIN - [save()]");
		
		return pensionDetailRepository.save(pensionDetail);
	}

}
