package com.cognizant.mfpe.processpensionmicroservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.mfpe.processpensionmicroservice.model.Bank;
import com.cognizant.mfpe.processpensionmicroservice.model.BankDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerDetail;
import com.cognizant.mfpe.processpensionmicroservice.model.PensionerInput;
import com.cognizant.mfpe.processpensionmicroservice.model.ProcessPensionInput;


// TODO: Auto-generated Javadoc
/**
 * The Class ProcesspensionmicroserviceApplicationTests.
 */
@SpringBootTest(classes = ProcesspensionmicroserviceApplicationTests.class)
class ProcesspensionmicroserviceApplicationTests {

	/**
	 * Context loads.
	 */
	@Test
	void contextLoads() {
	}

	/**
	 * Test no args bank.
	 */
	@Test
	void testNoArgsBank()
	{
		assertThat(new Bank()).isNotNull();
	}
	
	/**
	 * Test all args proess pension input.
	 */
	@Test
	void testAllArgsProessPensionInput()
	{
		ProcessPensionInput ps = new ProcessPensionInput("112233445566l", 23955.0, 500);
		assertThat(assertThat(ps).isNotNull());
	}
	
	/**
	 * Test no args process pension input.
	 */
	@Test
	void testNoArgsProcessPensionInput()
	{
		assertThat(new ProcessPensionInput()).isNotNull();
	}
	
	/**
	 * Test no args pensioner input.
	 */
	@Test
	void testNoArgsPensionerInput()
	{
		assertThat(new PensionerInput()).isNotNull();
	}
	
	
	/**
	 * Test no args pensioner detail.
	 */
	@Test
	void testNoArgsPensionerDetail()
	{
		assertThat(new PensionerDetail()).isNotNull();
	}
	
	/**
	 * Test no args pension detail.
	 */
	@Test
	void testNoArgsPensionDetail()
	{
		assertThat(new PensionDetail()).isNotNull();
	}
	
	/**
	 * Test setter pension detail.
	 */
	@Test
	void testSetterPensionDetail()
	{
		PensionDetail pd= new PensionDetail();
		pd.setPensionerDob(LocalDate.now());
		pd.setPensionerName("Mounika");
		pd.setPan("GTYIK7412L");
		pd.setPensionAmount(29950.0);
		pd.setPensionType("family");
		assertThat(assertThat(pd).isNotNull());
	}
	
	/**
	 * Test setter bank.
	 */
	@Test
	void testSetterBank()
	{
		Bank bank = new Bank();
		bank.setName("SBI");
		bank.setBankType("public");
		assertThat(assertThat(bank).isNotNull());
	}
	
	/**
	 * Test setter process pension input test.
	 */
	@Test
	void testSetterProcessPensionInputTest()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadharNumber("102233445566l");
		processPensionInput.setPensionAmount(45500.0);
		processPensionInput.setBankCharge(500);
		assertThat(assertThat(processPensionInput).isNotNull());
	}
	
	/**
	 * Test setter pensioner detail.
	 */
	@Test
	void testSetterPensionerDetail()
	{
		PensionerDetail pensionerDetail=new PensionerDetail();
		BankDetail bank=new BankDetail();
		bank.setAccountNumber("11223344");
		bank.setBankName("SBI");
		bank.setBankType("public");
		pensionerDetail.setPensionerDob(LocalDate.of(1999,12,10));
		pensionerDetail.setPensionerName("Mounika");
		pensionerDetail.setPan("ITHYU1236L");
		pensionerDetail.setAllowance(45000.0);
		pensionerDetail.setBank(bank);
		pensionerDetail.setSalary(100000.0);
		pensionerDetail.setPensionType("family");
		assertThat(assertThat(pensionerDetail).isNotNull());
	}
	
	/**
	 * Test setter pensioner input.
	 */
	@Test
	void testSetterPensionerInput()
	{
		PensionerInput pensionerInput=new PensionerInput();
		pensionerInput.setAadharNumber("112233445566l");
		pensionerInput.setPensionerName("Mounika");
		pensionerInput.setPensionerDob(LocalDate.of(1999, 12, 10));
		pensionerInput.setPan("ITHYU1236L");
		pensionerInput.setPensionType("family");
		assertThat(assertThat(pensionerInput).isNotNull());
	}
	
}

