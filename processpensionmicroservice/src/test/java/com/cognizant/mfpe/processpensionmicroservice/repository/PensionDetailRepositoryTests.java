package com.cognizant.mfpe.processpensionmicroservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;



// TODO: Auto-generated Javadoc
/**
 * The Class PensionDetailRepositoryTests.
 */
@DataJpaTest
class PensionDetailRepositoryTests {

	/** The entity manager. */
	@Autowired
	private TestEntityManager entityManager;

	/** The pension detail repository. */
	@Autowired
	private PensionDetailRepository pensionDetailRepository;

	/**
	 * Test repository is empty.
	 */
	@Test
	@DirtiesContext
	void testRepositoryIsEmpty() {
		Iterable<PensionDetail> pensionDetails = pensionDetailRepository.findAll();

		assertThat(pensionDetails).isEmpty();
	}

	/**
	 * Test save pension detail.
	 */
	@Test
	@DirtiesContext
	void testSavePensionDetail() {
		PensionDetail pensionDetail = pensionDetailRepository
				.save(new PensionDetail("Shivam",LocalDate.of(1998,8,3), "FBJP1234", "self", 10000));

		assertThat(pensionDetail).hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("pensionerName", "Shivam")
			.hasFieldOrPropertyWithValue("pensionerDob",LocalDate.of(1998,8,3))
			.hasFieldOrPropertyWithValue("pan", "FBJP1234")
			.hasFieldOrPropertyWithValue("pensionType", "self")
			.hasFieldOrPropertyWithValue("pensionAmount", 10000.0);
	}

	/**
	 * Test find all pension details.
	 */
	@Test
	@DirtiesContext
	void testFindAllPensionDetails() {
		entityManager.persist(new PensionDetail("Shivam",LocalDate.of(1998,8,3), "FBJP1234", "self", 10000));
		entityManager.persist(new PensionDetail("Sam",LocalDate.of(1998,8,3), "GHDK1234", "family", 20000));
		entityManager.persist(new PensionDetail("Jon", LocalDate.of(1998,8,3), "UIFH1234", "self", 30000));

		Iterable<PensionDetail> pensionDetails = pensionDetailRepository.findAll();
		assertThat(pensionDetails).hasSize(3);
	}

	/**
	 * Test find pension detail by id.
	 */
	@Test
	@DirtiesContext
	void testFindPensionDetailById() {
		PensionDetail pensionDetail = new PensionDetail("Shivam", LocalDate.of(1998,8,3), "FBJP1234", "self", 10000);
		entityManager.persist(pensionDetail);

		PensionDetail foundPensionDetail = pensionDetailRepository.findById(pensionDetail.getId()).get();
		assertThat(foundPensionDetail).isEqualTo(pensionDetail);
	}

	/**
	 * Test delete pension detail by id.
	 */
	@Test
	@DirtiesContext
	void testDeletePensionDetailById() {
		entityManager.persist(new PensionDetail("Shivam",LocalDate.of(1998,8,3), "FBJP1234", "self", 10000));
		entityManager.persist(new PensionDetail("Sam",LocalDate.of(1998,8,3), "GHDK1234", "family", 20000));
		entityManager.persist(new PensionDetail("Jon",LocalDate.of(1998,8,3), "UIFH1234", "self", 30000));

		pensionDetailRepository.deleteById(1L);

		Iterable<PensionDetail> pensionDetails = pensionDetailRepository.findAll();
		assertThat(pensionDetails).hasSize(2);
	}

	/**
	 * Test delete all.
	 */
	@Test
	@DirtiesContext
	void testDeleteAll() {
		entityManager.persist(new PensionDetail("Shivam",LocalDate.of(1998,8,3), "FBJP1234", "self", 10000));
		entityManager.persist(new PensionDetail("Sam", LocalDate.of(1998,8,3), "GHDK1234", "family", 20000));

		pensionDetailRepository.deleteAll();
		assertThat(pensionDetailRepository.findAll()).isEmpty();
	}

}
