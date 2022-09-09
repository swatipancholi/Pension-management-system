package com.cognizant.mfpe.processpensionmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;

/**
 * The Interface PensionDetailRepository.
 */
public interface PensionDetailRepository extends JpaRepository<PensionDetail, Long> {

}
